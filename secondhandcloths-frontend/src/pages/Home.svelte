<script>
    import axios from "axios";

    // TODO: Setze hier die URL zu deinem mit Postman erstellten Mock Server
    const api_root = "http://localhost:8080";

    let inserate = [];

    $: {
        getInserate();
    }

    function getInserate() {
        var config = {
            method: "get",
            url: api_root + "/api/inserat/home",
            headers: {},
        };

        axios(config)
            .then(function (response) {
                inserate = response.data;
            })
            .catch(function (error) {
                alert("Could not get Inserate");
                console.log(error);
            });
    }

</script>

<h1>Welcome to Second Hand Cloths!</h1>

<h2>Alle Inserate</h2>

<div class="row row-cols-1 row-cols-md-3 g-4">
    
        {#each inserate as inserat}
        <div class="col">
            <div class="card" style="width: 27rem;">
                <img src="pictures/{inserat.filename}" class="card-img-top" alt="" />
                <div class="card-body">
                    <h5 class="card-title">{inserat.titel}</h5>
                    <p class="card-text">{inserat.beschreibung}</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">{inserat.preis}</li>
                    <li class="list-group-item">{inserat.kategorie}</li>
                </ul>
            </div>
        </div>
        {/each}
</div>
