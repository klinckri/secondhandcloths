<script>
    import axios from "axios";

    const api_root = "http://localhost:8080";

    let inserat = {
        titel: null,
        beschreibung: null,
        preis: null,
        iban: null,
        kategorie: null,
        personId: "637c6673498da731157c71c8",
        file: null,
    };

    let input;
    let container;
    let image;
    let placeholder;
    let showImage = false;

    function onChange() {
        const file = input.files[0];

        if (file) {
            showImage = true;

            const reader = new FileReader();
            reader.addEventListener("load", function () {
                image.setAttribute("src", reader.result);
            });
            reader.readAsDataURL(file);
            inserat.file = file;
            return;
        }
        showImage = false;
    }

    function inserieren() {
        var config = {
            method: "post",
            url: api_root + "/api/inserat/inserieren",
            headers: {
                "Content-Type": "application/json",
            },
            data: inserat,
        };

        axios(config)
            .then(function (response) {
                alert("Inserat wurde erstellt");
                window.location.href = "#/home";
            })
            .catch(function (error) {
                alert("Inserat konnte nicht erstellt werden");
                console.log(error);
            });
    }
</script>

<h1 class="mt-3">Inserat erstellen</h1>
<form class="mb-5">
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="titel">Titel</label>
            <input
                bind:value={inserat.titel}
                class="form-control"
                id="titel"
                type="text"
            />
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="beschreibung">Beschreibung</label>
            <input
                bind:value={inserat.beschreibung}
                class="form-control"
                id="beschreibung"
                type="text"
            />
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="preis">Preis</label>
            <input
                bind:value={inserat.preis}
                class="form-control"
                id="preis"
                type="number"
            />
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="iban">IBAN</label>
            <input
                bind:value={inserat.iban}
                class="form-control"
                id="iban"
                type="text"
            />
        </div>
    </div>
    <div class="row mb-3">
        <div class="col">
            <label class="form-label" for="type">Kategorie</label>
            <select
                bind:value={inserat.kategorie}
                class="form-select"
                id="type"
                type="text"
            >
                <option value="KLEIDER">KLEIDER</option>
                <option value="HOSEN">HOSEN</option>
                <option value="PULLOVER">PULLOVER</option>
                <option value="BLAZER">BLAZER</option>
                <option value="ANZUG">ANZUG</option>
                <option value="JACKEN">JACKEN</option>
                <option value="TOPS">TOPS</option>
                <option value="UNTERWAESCHE">UNTERWAESCHE</option>
                <option value="SCHUHE">SCHUHE</option>
                <option value="ANDERE">ANDERE</option>
            </select>
        </div>
        <div class="col">
            <label class="form-label" for="person">Person</label>
            <input
                bind:value={inserat.personId}
                class="form-control"
                id="person"
                type="text"
            />
        </div>
    </div>
    <div class="row mb-3">
        <label class="form-label" for="bild">Bild hochladen</label>
        <input bind:this={input} on:change={onChange} type="file" />
        <div bind:this={container}>
            {#if showImage}
                <img bind:this={image} src="" alt="Preview" />
            {:else}
                <span bind:this={placeholder}>Bildvorschau</span>
            {/if}
        </div>
    </div>
    <button type="button" class="btn btn-primary" on:click={inserieren}>Erstellen</button>
</form>
