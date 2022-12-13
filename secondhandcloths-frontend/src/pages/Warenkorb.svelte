<script>
    import axios from "axios";
    import { isAuthenticated, jwt_token, anzahlInserate } from "../store";

    const api_root = "http://localhost:8080";

    let inserate = [];

    $: {
        getWarenkorb();
    }

    function getWarenkorb() {
        var config = {
            method: "get",
            url: api_root + "/api/kauf/warenkorb",
            headers: { Authorization: "Bearer " + $jwt_token },
        };

        axios(config)
            .then(function (response) {
                inserate = response.data;
                getWarenkorbSize();
            })
            .catch(function (error) {
                alert("Could not get Inserate");
                console.log(error);
            });
    }

    function getWarenkorbSize() {
        var config = {
            method: "get",
            url: api_root + "/api/kauf/warenkorbSize",
            headers: { Authorization: "Bearer " + $jwt_token },
        };

        axios(config)
            .then(function (response) {
                anzahlInserate.set(response.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }

    function removeFromBasekt(id) {
        var config = {
            method: "post",
            url: api_root + "/api/kauf/removeFromBasket/" + id,
            headers: { Authorization: "Bearer " + $jwt_token },
        };

        axios(config)
            .then(function () {
                getWarenkorb()
            })
            .catch(function (error) {
                alert("Could not remove from basket");
                console.log(error);
            });
    }
</script>
{#if $isAuthenticated}
<h2>Mein Warenkorb</h2>

<div class="row row-cols-1 row-cols-md-3 g-4">
    {#each inserate as inserat}
            <div class="col">
                <div class="card bg-light shadow-sm p-3 mb-5 bg-body rounded" style="width: 27rem;">
                    <img
                        src="pictures/{inserat.filename}"
                        class="card-img-top"
                        alt="inserat"
                    />
                    <div class="card-body">
                        <h5 class="card-title">{inserat.titel}</h5>
                        <p class="card-text">{inserat.beschreibung}</p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">{inserat.preis}</li>
                        <li class="list-group-item">{inserat.kategorie}</li>
                        <li class="list-group-item"><button class="btn btn-outline-secondary" on:click={removeFromBasekt(inserat.id)}><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                          </svg>Aus dem Warenkorb entfernen</button></li>
                    </ul>
                </div>
            </div>
    {/each}
</div>
{/if}