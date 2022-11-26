<script>
    import axios from "axios";

    // TODO: Setze hier die URL zu deinem mit Postman erstellten Mock Server
    const api_root = "http://localhost:8080";

    let inserate = [];
    let inserat = {
        titel: null,
        beschreibung: null,
        preis: null,
        kategorie: null,
    }

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
                inserate = response.data.content;
            })
            .catch(function (error) {
                alert("Could not get Inserate");
                console.log(error);
            });
    }
</script>

<h1>Welcome to Second Hand Cloths!</h1>

<h2>Alle Inserate</h2>
<table class="table">
    <thead>
        <tr>
            <th scope="col">Titel</th>
            <th scope="col">Beschreibung</th>
            <th scope="col">Preis</th>
            <th scope="col">Kategorie</th>
        </tr>
    </thead>
    <tbody>
        {#each inserate as inserat}
            <tr>
                <td>{inserat.titel}</td>
                <td>{inserat.beschreibung}</td>
                <td>{inserat.preis}</td>
                <td>{inserat.kategorie}</td>
            </tr>
        {/each}
    </tbody>
</table>