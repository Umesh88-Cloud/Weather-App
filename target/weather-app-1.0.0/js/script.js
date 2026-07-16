async function getWeather() {

    const city = document.getElementById("city").value;

    if (city === "") {
        alert("Please enter a city.");
        return;
    }

    const response = await fetch("weather?city=" + encodeURIComponent(city));

    const data = await response.text();

    document.getElementById("result").textContent = data;
}
