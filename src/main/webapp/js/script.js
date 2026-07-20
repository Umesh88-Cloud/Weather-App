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

        // Show loading spinner + text
        loader.style.display = "block";

        // Hide old weather card while loading
        if(weatherCard){
            weatherCard.style.display = "none";
        }
    });
}