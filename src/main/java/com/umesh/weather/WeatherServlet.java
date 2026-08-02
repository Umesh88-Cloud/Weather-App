package com.umesh.weather;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {

    private WeatherService service = new WeatherService();

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("WeatherServlet Called");

        // Get city or coordinates
        String city = request.getParameter("city");
        String lat = request.getParameter("lat");
        String lon = request.getParameter("lon");

        String weatherData;
        String forecastData = null;

        // If latitude and longitude are available
        if (lat != null && !lat.isEmpty()
                && lon != null && !lon.isEmpty()) {

            // Weather by coordinates
            weatherData = service.getWeatherByCoordinates(lat, lon);

        } else {

            // Weather by city
            weatherData = service.getWeather(city);

            // Forecast by city
            forecastData = service.getForecast(city);
        }

        // ===== CHECK 1: API fetch error =====
        if (weatherData.contains("error")) {

            request.setAttribute(
                    "error",
                    "Unable to fetch weather data. Please try again.");

            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);

            return;
        }

        // Convert JSON string to object
        JSONObject json = new JSONObject(weatherData);

        // ===== CHECK 2: Invalid city =====
        if (json.has("cod") && json.getInt("cod") != 200) {

            request.setAttribute(
                    "error",
                    "City not found. Please enter a valid city name.");

            request.getRequestDispatcher("index.jsp")
                    .forward(request, response);

            return;
        }

        // City Name
        String cityName = json.getString("name");

        // Main Weather Data
        JSONObject main = json.getJSONObject("main");
        double temperature = main.getDouble("temp");
        int humidity = main.getInt("humidity");
        int pressure = main.getInt("pressure");

        // Wind Data
        JSONObject wind = json.getJSONObject("wind");
        double windSpeed = wind.getDouble("speed");

        // Sunrise & Sunset
        JSONObject sys = json.getJSONObject("sys");

        long sunrise = sys.getLong("sunrise");
        long sunset = sys.getLong("sunset");

        java.text.SimpleDateFormat timeFormat = new java.text.SimpleDateFormat("hh:mm a");

        String sunriseTime = timeFormat.format(new java.util.Date(sunrise * 1000));

        String sunsetTime = timeFormat.format(new java.util.Date(sunset * 1000));

        // Weather Data
        JSONArray weatherArray = json.getJSONArray("weather");

        String description = weatherArray.getJSONObject(0)
                .getString("description");

        String mainWeather = weatherArray.getJSONObject(0)
                .getString("main");

        String icon = weatherArray.getJSONObject(0)
                .getString("icon");

        String iconUrl = "https://openweathermap.org/img/wn/"
                + icon
                + "@2x.png";

        // Send data to JSP
        request.setAttribute("city", cityName);
        request.setAttribute("temperature", temperature);
        request.setAttribute("humidity", humidity);
        request.setAttribute("pressure", pressure);
        request.setAttribute("windSpeed", windSpeed);
        request.setAttribute("sunrise", sunriseTime);
        request.setAttribute("sunset", sunsetTime);
        request.setAttribute("description", description);
        request.setAttribute("iconUrl", iconUrl);
        request.setAttribute("mainWeather", mainWeather);

        // ===== FORECAST DATA =====
        if (forecastData != null && !forecastData.equals("error")) {

            JSONObject forecastJson = new JSONObject(forecastData);

            JSONArray forecastList = forecastJson.getJSONArray("list");

            request.setAttribute("forecastList", forecastList);
        }

        // Show current location badge if coordinates were used
        if (lat != null && !lat.isEmpty()) {
            request.setAttribute("isCurrentLocation", true);
        }

        // Forward to JSP
        request.getRequestDispatcher("index.jsp")
                .forward(request, response);
    }
}