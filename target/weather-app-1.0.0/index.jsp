<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Weather App</title>

<link rel="stylesheet" href="css/style.css">

</head>

<body>

<div class="container">

    <h1>🌦 Weather App</h1>

    <form action="weather" method="post">

        <input
            type="text"
            name="city"
            placeholder="Enter City"
            value="<%= request.getParameter("city") != null ? request.getParameter("city") : "" %>"
            required>

        <button type="submit">
            Search
        </button>

    </form>

    <% if(request.getAttribute("city") != null){ %>

<div class="weather-card">

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
        <span>☁ Weather</span>
        <strong><%= request.getAttribute("description") %></strong>
    </p>

</div>

<% } %>

</div>

</body>

</html>