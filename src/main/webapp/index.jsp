<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Weather App</title>

    <link rel="stylesheet" href="css/style.css">
</head>

<body>

    <div class="container">

        <h1>🌤 Weather App</h1>

        <input
            type="text"
            id="city"
            placeholder="Enter city name">

        <button onclick="getWeather()">
            Search
        </button>

        <pre id="result"></pre>

    </div>

    <script src="js/script.js"></script>

</body>
</html>