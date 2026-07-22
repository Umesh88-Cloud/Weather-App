// =========================
// FORM + LOADER
// =========================

const form = document.querySelector("form");
const loader = document.getElementById("loading");
const weatherCard = document.querySelector(".weather-card");

// Hide loader when page finishes loading
window.addEventListener("load", () => {
    if(loader){
        loader.style.display = "none";
    }
});

// Show loader when form is submitted
if(form && loader){

    form.addEventListener("submit", () => {

        loader.style.display = "block";

        if(weatherCard){
            weatherCard.style.display = "none";
        }
    });
}

// =========================
// GEOLOCATION
// =========================

const locationBtn = document.getElementById("locationBtn");

if(locationBtn){

    locationBtn.onclick = function () {

        // Check browser support
        if(!navigator.geolocation){

            alert("Geolocation is not supported by your browser.");
            return;
        }

        // Change button text
        locationBtn.innerHTML = "📡 Detecting location...";
        locationBtn.disabled = true;

        // Get current position
        navigator.geolocation.getCurrentPosition(

            // SUCCESS
            function(position){

                const latitude = position.coords.latitude;
                const longitude = position.coords.longitude;

                alert("Location detected!");

                console.log("Latitude:", latitude);
                console.log("Longitude:", longitude);

                // Put values into hidden fields
                document.getElementById("lat").value = latitude;
                document.getElementById("lon").value = longitude;

                // Submit form automatically
                form.submit();
            },

            // ERROR
            function(error){

                alert("Location access denied or unavailable.");

                console.error(error);

                locationBtn.innerHTML = "📍 Use My Location";
                locationBtn.disabled = false;
            }
        );
    };
}

// =========================
// RECENT SEARCHES
// =========================

const cityInput = document.querySelector('input[name="city"]');
const recentList = document.getElementById("recentList");

// Load recent searches when page opens
window.addEventListener("load", loadRecentSearches);

// Save search on form submit
if(form && cityInput){

    form.addEventListener("submit", () => {

        const city = cityInput.value.trim();

        if(city){
            saveRecentSearch(city);
        }
    });
}

// Save city to localStorage
function saveRecentSearch(city){

    let searches = JSON.parse(localStorage.getItem("recentSearches")) || [];

    // Remove duplicate
    searches = searches.filter(item =>
        item.toLowerCase() !== city.toLowerCase()
    );

    // Add new city at beginning
    searches.unshift(city);

    // Keep only last 5
    searches = searches.slice(0, 5);

    localStorage.setItem("recentSearches", JSON.stringify(searches));
}

// Display recent searches
function loadRecentSearches(){

    if(!recentList) return;

    const searches = JSON.parse(localStorage.getItem("recentSearches")) || [];

    recentList.innerHTML = "";

    searches.forEach(city => {

        const chip = document.createElement("button");

        chip.type = "button";
        chip.className = "recent-item";
        chip.textContent = city;

        chip.addEventListener("click", () => {

            cityInput.value = city;
            form.submit();
        });

        recentList.appendChild(chip);
    });
}