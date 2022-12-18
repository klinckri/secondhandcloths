<script>
    import axios from "axios";
    import { isAuthenticated, jwt_token } from "../store";

    const api_root = "http://localhost:8080";
    let weather = {
        location: "",
        current: "",
    };

    let city;

    function getWeather() {
        let query = "city=" + city;
        var config = {
            method: "get",
            url: api_root + "/api/weather?" + query,
        };

        axios(config)
            .then(function (response) {
                weather = response.data;
                document.getElementById("icon").innerHTML= '<img src="'+weather.current.condition.icon+'"/>';
                document.getElementById("text").innerHTML= weather.current.condition.text;
                document.getElementById("temp").innerHTML= weather.current.temp_c;
                document.getElementById("feelslike").innerHTML= weather.current.feelslike_c;
                document.getElementById("wind").innerHTML= weather.current.wind_kph;
                document.getElementById("humidity").innerHTML= weather.current.humidity;
            })
            .catch(function (error) {
                alert("Ungültige Stadt");
                console.log(error);
            });
    }
</script>
    <div class="shadow-sm p-3 mb-5 bg-body rounded">
        <h1 class="mt-3">Wettervorhersage</h1>
        <form class="mb-5 form-group">
            <div class="row mb-3">
                <div class="col">
                    <label class="form-label" for="titel">Ort</label>
                    <input bind:value={city} class="form-control" id="titel" type="text" />
                </div>
            </div>
            <div class="d-grid gap-2">
                <button
                    type="button"
                    class="btn btn-outline-secondary"
                    on:click={getWeather}>Wetterprognoze ansehen</button>
            </div>
        </form>
    </div>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <table class="table shadow-sm p-3 mb-5 bg-body rounded">
            <thead>
              <tr>
                <th scope="col"></th>
                <th scope="col"></th>
                <th scope="col">Temperatur in Celsius</th>
                <th scope="col">Feels like in Celsius</th>
                <th scope="col">Wind kmh</th>
                <th scope="col">Humidity</th>
              </tr>
            </thead>
            <tbody>
                    <tr>
                        <td><div id="icon"></div></td>
                        <td><div id="text"></div></td>
                        <td><div id="temp"></div></td>
                        <td><div id="feelslike"></div></td>
                        <td><div id="wind"></div></td>
                        <td><div id="humidity"></div></td>
                    </tr>
            </tbody>
          </table>
    </div>