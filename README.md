# 🌦 Weather App

A Java Servlet & JSP based Weather Application that fetches real-time weather information using the OpenWeather API.

---

## 🚀 Features

* Search weather by city
* Fetch live weather data
* Display City Name
* Display Temperature
* Display Humidity
* Display Pressure
* Display Wind Speed
* Display Weather Description
* Display real OpenWeather weather icons
* Dynamic background based on weather conditions
* Loading spinner while fetching weather data
* Friendly error messages for invalid cities
* Smooth animations and hover effects
* Responsive and modern UI
* 📍 Use My Location button (Geolocation API)
* Fetch weather using latitude and longitude
* Display current location badge
* Recent searches saved in browser localStorage
* Recent search buttons displayed in a single horizontal line
* Smooth weather card appear animation
* 🌤 5-Day Weather Forecast
* Forecast cards with weather icons
* Forecast temperature display
* Forecast date formatting (DD-MM-YYYY)
* Horizontal forecast card layout
* Clean and responsive forecast UI

---

## 🛠 Technologies

* Java 17
* Maven
* Jakarta Servlet
* JSP
* HTML
* CSS
* JavaScript
* OpenWeather API
* JSON Library (org.json)
* Apache Tomcat 11
* Git
* GitHub

---

## 📅 Project Status

🚧 Under Development

### ✅ Day 1 Completed

* Planned the project
* Created GitHub repository
* Installed Java
* Installed Maven
* Installed Apache Tomcat
* Created basic Maven project

---

### ✅ Day 2 Completed

* Created Maven Web Application structure
* Configured `pom.xml`
* Created package structure
* Created `WeatherServlet.java`
* Created `WeatherService.java`
* Created `index.jsp`
* Created `style.css`
* Created `script.js`
* Built WAR file successfully using Maven
* Deployed application on Apache Tomcat
* Successfully ran the application on localhost

---

### ✅ Day 3 Completed

* Created OpenWeather account
* Generated OpenWeather API Key
* Connected application with OpenWeather API
* Successfully fetched live weather data
* Added JSON library (`org.json`) dependency
* Parsed JSON response in `WeatherServlet`
* Extracted:
  * City Name
  * Temperature
  * Humidity
  * Pressure
  * Wind Speed
  * Weather Description
* Displayed live weather information on the webpage
* Tested application successfully on Apache Tomcat

---

### ✅ Day 4 Completed

* Redesigned the complete UI
* Added gradient background
* Added glassmorphism container effect
* Created modern weather card layout
* Added real OpenWeather weather icons
* Improved typography and spacing
* Added button hover animations
* Improved search form design
* Made the application responsive for mobile devices
* Enhanced overall user experience

---

### ✅ Day 5 Completed

* Added dynamic background based on weather conditions
* Implemented temperature-based sunny background
* Added loading spinner animation
* Displayed "Fetching weather data..." while searching
* Hid previous weather card during loading
* Added friendly error messages for invalid cities
* Added footer with developer information and GitHub link
* Displayed footer only on the home page
* Added smooth hover animations and UI transitions
* Improved overall UI polish and user experience

---

### ✅ Day 6 Completed

* Added 🔍 Search button with icon
* Added 📍 Use My Location button with green theme
* Implemented browser Geolocation API
* Requested location permission from the user
* Captured current latitude and longitude
* Sent coordinates to `WeatherServlet`
* Fetched weather using coordinates
* Added `getWeatherByCoordinates()` in `WeatherService`
* Displayed 📍 Current Location badge on the weather card
* Saved recent searches using localStorage
* Displayed recent search buttons in a single horizontal line
* Added smooth weather card appear animation
* Improved mobile scrolling for recent searches
* Refactored API handling for cleaner code structure

---

### ✅ Day 7 Completed

* Added 5-Day Weather Forecast feature
* Integrated OpenWeather 5-Day Forecast API
* Added `getForecast()` method in `WeatherService`
* Parsed forecast JSON in `WeatherServlet`
* Displayed forecast cards below current weather
* Added weather icons for each forecast day
* Displayed forecast temperature
* Changed forecast date format from `YYYY-MM-DD` to `DD-MM-YYYY`
* Created responsive horizontal forecast layout
* Added forecast card hover effects
* Improved overall forecast UI and user experience
* Refactored code for better readability and maintainability

---

## 🎯 (Day 8)

* Add Air Quality Index (AQI)
* Display AQI level (Good, Moderate, Poor, etc.)
* Show visibility information
* Show UV Index
* Display "Feels Like" temperature
* Add sunrise and sunset icons with better UI
* Improve weather animations

---

## 👨‍💻 Developer

**Umesh Rupnar**
