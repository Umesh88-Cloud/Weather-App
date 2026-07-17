package com.umesh.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    // Replace this with your OpenWeather API Key later
    private static final String API_KEY = "6357d0bba373b68fef7863d539f351d3";

    public String getWeather(String city) {

        try {

            String urlString =
                    "https://api.openweathermap.org/data/2.5/weather?q="
                    + city
                    + "&appid="
                    + API_KEY
                    + "&units=metric";

            URL url = new URL(urlString);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));

            StringBuilder response = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            return response.toString();

        } catch (Exception e) {
            return "{\"error\":\"Unable to fetch weather\"}";
        }

    }

}