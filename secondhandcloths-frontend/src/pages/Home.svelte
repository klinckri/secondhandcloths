<script>
    import axios from "axios";
    import { isAuthenticated, user, jwt_token, anzahlInserate} from "../store";

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
    
    function addToCart(id) {
        var config = {
            method: "post",
            url: api_root + "/api/kauf/addToWarenkorb/" + id,
            headers: { Authorization: "Bearer " + $jwt_token },
        };

        axios(config)
            .then(function () {
                getInserate();
                getWarenkorb();
            })
            .catch(function (error) {
                alert(
                    "Der Artikel konnte nicht in den Warenkorb gelegt werden."
                );
                console.log(error);
            });
    }

    function getWarenkorb() {
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

    function deleteItem(id) {
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

<h1>Welcome to Second Hand Cloths!</h1>

<div class="row row-cols-1 row-cols-md-3 g-4">
    {#each inserate as inserat}
        {#if inserat.inseratState == "INSERIERT"}
            <div class="col">
                <div
                    class="card bg-light shadow-sm p-3 mb-5 bg-body rounded"
                    style="width: 27rem;"
                >
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
                        <li class="list-group-item">
                            Preis in CHF: {inserat.preis}
                        </li>
                        <li class="list-group-item">
                            Kategorie: {inserat.kategorie}
                        </li>
                        {#if $isAuthenticated}
                            <li class="list-group-item">
                                <button
                                    on:click={addToCart(inserat.id)}
                                    class="btn btn-outline-secondary"
                                    ><svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        width="16"
                                        height="16"
                                        fill="currentColor"
                                        class="bi bi-cart-plus"
                                        viewBox="0 0 16 16"
                                    >
                                        <path
                                            d="M9 5.5a.5.5 0 0 0-1 0V7H6.5a.5.5 0 0 0 0 1H8v1.5a.5.5 0 0 0 1 0V8h1.5a.5.5 0 0 0 0-1H9V5.5z"
                                        />
                                        <path
                                            d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zm3.915 10L3.102 4h10.796l-1.313 7h-8.17zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"
                                        />
                                    </svg> In den Warenkorb legen</button
                                >
                            </li>
                        {/if}
                        {#if $isAuthenticated && $user.user_roles && $user.user_roles.includes("admin")}
                            <li class="list-group-item">
                                <button class="btn btn-outline-secondary" on:click={deleteItem(inserat.id)}>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16" >
                                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                        <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                                    </svg> Inserat löschen</button>
                            </li>
                        {/if}
                    </ul>
                </div>
            </div>
        {/if}
    {/each}
</div>
