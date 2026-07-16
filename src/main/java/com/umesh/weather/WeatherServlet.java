package com.umesh.weather;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private WeatherService weatherService = new WeatherService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String city = request.getParameter("city");

        if (city == null || city.trim().isEmpty()) {
            response.setContentType("text/plain");
            response.getWriter().write("Please enter a city name.");
            return;
        }

        String weatherData = weatherService.getWeather(city);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(weatherData);
    }
}