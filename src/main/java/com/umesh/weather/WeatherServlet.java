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

        // Get city from form
        String city = request.getParameter("city");

        // Get weather JSON from API
        String weatherData = service.getWeather(city);

        // Parse JSON
        JSONObject json = new JSONObject(weatherData);

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

        // Weather Description
        JSONArray weatherArray = json.getJSONArray("weather");
        String description = weatherArray.getJSONObject(0).getString("description");
        String icon = weatherArray.getJSONObject(0).getString("icon");
        String iconUrl = "https://openweathermap.org/img/wn/" + icon + "@2x.png";

        // Send data to JSP
        request.setAttribute("city", cityName);
        request.setAttribute("temperature", temperature);
        request.setAttribute("humidity", humidity);
        request.setAttribute("pressure", pressure);
        request.setAttribute("windSpeed", windSpeed);
        request.setAttribute("description", description);
        request.setAttribute("iconUrl", iconUrl);

        // Forward to index.jsp
        request.getRequestDispatcher("index.jsp")
               .forward(request, response);
    }
}