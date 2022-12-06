<script>
    import { user } from "../store";
    import axios from "axios";

    // TODO: Setze hier die URL zu deinem mit Postman erstellten Mock Server
    const api_root = "http://localhost:8080";

    let inserate = [];

    $: {
        getInserate();
    }

    let file;
    let input;
    function getInserate() {
        var config = {
            method: "get",
            url: api_root + "/api/inserat/637c6673498da731157c71c8",
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

    function deleteInserat(id) {
        var config = {
            method: "delete",
            url: api_root + "/api/inserat/delete/" + id,
            headers: {},
        };
        axios(config)
            .then(function (response) {
                getInserate();
            })
            .catch(function (error) {
                alert("Could not delete Inserat");
                console.log(error);
            });
    }
</script>

<h1>Account Details</h1>
<img src="{$user.picture}" alt="profilePicture"> 
<p>Name: {$user.name}</p>
<p>Nickname: {$user.nickname}</p>
<p>Email: {$user.email}</p>

<h2>Meine Inserate</h2>

<div class="row row-cols-1 row-cols-md-3 g-4">
    {#each inserate as inserat}
        <div class="col">
            <div class="card" style="width: 27rem;">
                <img src="" class="card-img-top" alt="" />
                <div class="card-body">
                    <h5 class="card-title">{inserat.titel}</h5>
                    <p class="card-text">{inserat.beschreibung}</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">{inserat.preis}</li>
                    <li class="list-group-item">{inserat.kategorie}</li>
                    <li class="list-group-item">
                        <button on:click={deleteInserat(inserat.id)}>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16" >
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                            </svg> Inserat löschen</button>
                    </li>
                </ul>
            </div>
        </div>
    {/each}
</div>
