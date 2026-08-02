<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Weather App</title>

    <link rel="stylesheet" href="css/style.css?v=8">
</head>

<%
    String weatherType = (String) request.getAttribute("mainWeather");
    Double temp = (Double) request.getAttribute("temperature");

    String bodyClass = "default-bg";

    if(weatherType != null){

        if(temp != null && temp >= 32){
            bodyClass = "sunny";

        } else if(weatherType.equalsIgnoreCase("Clear")){
            bodyClass = "sunny";

        } else if(weatherType.equalsIgnoreCase("Clouds")){
            bodyClass = "cloudy";

        } else if(weatherType.equalsIgnoreCase("Rain") ||
                  weatherType.equalsIgnoreCase("Drizzle")){
            bodyClass = "rainy";

        } else if(weatherType.equalsIgnoreCase("Thunderstorm")){
            bodyClass = "storm";
        }
    }
%>

<body class="<%= bodyClass %>">

<div class="container">

    <h1>🌦 Weather App</h1>

    <form action="weather" method="post">

        <input
            type="text"
            name="city"
            placeholder="Enter city name"
            value="<%= request.getParameter("city") != null ? request.getParameter("city") : "" %>"
            required>

        <button type="submit" class="search-btn">
            🔍 Search
        </button>

        <button type="button"
                id="locationBtn"
                class="location-btn">
            📍 Use My Location
        </button>

        <input type="hidden" name="lat" id="lat">
        <input type="hidden" name="lon" id="lon">

    </form>


    <!--RECENT SEARCHES -->

    <div id="recentSearches" class="recent-searches">

        <h3>🕘 Recent Searches</h3>

        <div id="recentList" class="recent-list"></div>

    </div>


    <!--LOADING SPINNER -->

    <div id="loading" class="loader" style="display:none;">
        <div class="spinner"></div>
        <p>Fetching weather data...</p>
    </div>



    <!-- ERROR MESSAGE-->

    <% if(request.getAttribute("error") != null){ %>

        <div class="error-box">
            ❌ <%= request.getAttribute("error") %>
        </div>

    <% } %>


    <!--WEATHER CARD-->

    <% if(request.getAttribute("city") != null){ %>

        <p class="weather-type">
            Weather Type: <%= request.getAttribute("mainWeather") %>
        </p>

        <div class="weather-card">

            <% if(request.getAttribute("isCurrentLocation") != null){ %>

                <div class="location-badge">
                    📍 Current Location
                </div>

            <% } %>

            <div class="weather-header">

                <img src="<%= request.getAttribute("iconUrl") %>"
                     alt="Weather Icon"
                     class="weather-icon">

                <h2>📍 <%= request.getAttribute("city") %></h2>

            </div>

            <p>
                <span>🌡 Temperature</span>
                <strong><%= request.getAttribute("temperature") %> °C</strong>
            </p>

            <p>
                <span>💧 Humidity</span>
                <strong><%= request.getAttribute("humidity") %> %</strong>
            </p>

            <p>
                <span>📈 Pressure</span>
                <strong><%= request.getAttribute("pressure") %> hPa</strong>
            </p>

            <p>
                <span>🌬 Wind Speed</span>
                <strong><%= request.getAttribute("windSpeed") %> m/s</strong>
            </p>

            <p>
                <span>🌅 Sunrise</span>
                <strong><%= request.getAttribute("sunrise") %></strong>
            </p>

            <p>
                <span>🌇 Sunset</span>
                <strong><%= request.getAttribute("sunset") %></strong>
            </p>

            <p>
                <span>☁ Weather</span>
                <strong><%= request.getAttribute("description") %></strong>
            </p>

        </div>



        <!-- 5-DAY FORECAST-->

        <% if(request.getAttribute("forecastList") != null){ %>

            <div class="forecast-section">

                <h3>📅 5-Day Forecast</h3>

                <div class="forecast-container">

                    <%
                        org.json.JSONArray forecastList =
                            (org.json.JSONArray) request.getAttribute("forecastList");

                        int count = 0;

                        for(int i = 0; i < forecastList.length() && count < 5; i++){

                            org.json.JSONObject item =
                                forecastList.getJSONObject(i);

                            String dateTime = item.getString("dt_txt");

                            // Pick only 12:00 PM forecast
                            if(dateTime.contains("12:00:00")){

                                org.json.JSONObject mainForecast =
                                    item.getJSONObject("main");

                                double tempForecast =
                                    mainForecast.getDouble("temp");

                                org.json.JSONObject weatherForecast =
                                    item.getJSONArray("weather").getJSONObject(0);

                                String iconForecast =
                                    weatherForecast.getString("icon");

                                String descForecast =
                                    weatherForecast.getString("description");

                                String iconUrlForecast =
                                    "https://openweathermap.org/img/wn/"
                                    + iconForecast
                                    + "@2x.png";

                                // Convert 2026-07-24 → 24-07-2026
                                String[] parts = dateTime.substring(0, 10).split("-");

                                String dateOnly = parts[2] + "-" + parts[1] + "-" + parts[0];

                                count++;
                    %>

                    <div class="forecast-card">

                        <p class="forecast-date">
                            <%= dateOnly %>
                        </p>

                        <img src="<%= iconUrlForecast %>"
                             alt="Forecast Icon"
                             class="forecast-icon">

                        <p class="forecast-temp">
                            <%= String.format("%.1f", tempForecast) %>°C
                        </p>

                        <p class="forecast-desc">
                            <%= descForecast %>
                        </p>

                    </div>

                    <%
                            }
                        }
                    %>

                </div>

            </div>

        <% } %>

    <% } %>

</div>


<!--FOOTER-->

<% if(request.getAttribute("city") == null){ %>

<footer class="footer">

    <p>
        Developed by <strong>Umesh Rupnar</strong>
    </p>

    <a href="https://github.com/Umesh88-Cloud"
       target="_blank">
        🔗 View on GitHub
    </a>

</footer>

<% } %>

<script src="<%= request.getContextPath() %>/js/script.js?v=3"></script>

</body>
</html>