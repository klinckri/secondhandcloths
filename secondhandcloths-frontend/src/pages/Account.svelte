<script>
    import { isAuthenticated, user, jwt_token } from "../store";
    import axios from "axios";

    const api_root = window.location.origin;

    let inserate = [];

    $: {
        getInserate();
    }
    
    function getInserate() {
        var config = {
            method: "get",
            url: api_root + "/api/inserat/person",
            headers: { Authorization: "Bearer " + $jwt_token },
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
            headers: { Authorization: "Bearer " + $jwt_token },
        };
        axios(config)
            .then(function () {
                getInserate();
            })
            .catch(function (error) {
                alert("Could not delete Inserat");
                console.log(error);
            });
    }
</script>
{#if $isAuthenticated}
<h1>Account Details</h1>
<img src="{$user.picture}" alt="profilePicture"> 
<p>Name: {$user.name}</p>
<p>Nickname: {$user.nickname}</p>
<p>Email: {$user.email}</p>

<br>
<h2>Meine Inserate</h2>

<div class="row row-cols-1 row-cols-md-3 g-4">
    <table class="table shadow-sm p-3 mb-5 bg-body rounded">
        <thead>
          <tr>
            <th scope="col">Titel</th>
            <th scope="col">Beschreibung</th>
            <th scope="col">Preis in CHF</th>
            <th scope="col">Kategorie</th>
            <th scope="col">Inserat Status</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
            {#each inserate as inserat}
                <tr>
                    <td>{inserat.titel}</td>
                    <td>{inserat.beschreibung}</td>
                    <td>{inserat.preis}</td>
                    <td>{inserat.kategorie}</td>
                    <td>{inserat.inseratState}</td>
                    <td><button class="btn btn-outline-secondary" on:click={deleteInserat(inserat.id)}>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16" >
                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                        </svg> Inserat l??schen</button></td>
                </tr>
          {/each}
        </tbody>
      </table>
</div>
{/if}