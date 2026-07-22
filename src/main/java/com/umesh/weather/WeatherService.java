package com.umesh.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    // OpenWeather API Key
    private static final String API_KEY = "6357d0bba373b68fef7863d539f351d3";

    
    // GET WEATHER BY CITY
    
    public String getWeather(String city) {

        String urlString =
                "https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&appid="
                + API_KEY
                + "&units=metric";

        return fetchWeather(urlString);
    }

  
    // GET WEATHER BY COORDINATES
    
    public String getWeatherByCoordinates(String lat, String lon) {

        String urlString =
                "https://api.openweathermap.org/data/2.5/weather?lat="
                + lat
                + "&lon="
                + lon
                + "&appid="
                + API_KEY
                + "&units=metric";

        return fetchWeather(urlString);
    }

    
    // COMMON API CALL METHOD
  
    private String fetchWeather(String urlString) {

        try {

            URL url = new URL(urlString);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader reader;

            // Success response
            if (connection.getResponseCode() >= 200
                    && connection.getResponseCode() < 300) {

                reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

            } else {

                // Error response (invalid city, etc.)
                reader = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream()));
            }

            StringBuilder response = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            return response.toString();

        } catch (Exception e) {

            e.printStackTrace();

            return "error";
        }
    }
}