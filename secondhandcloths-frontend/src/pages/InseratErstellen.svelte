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
    };

    let input;
    let formData = new FormData();

    function inserieren() {
        formData.append('titel', inserat.titel)
        formData.append('beschreibung', inserat.beschreibung)
        formData.append('preis', inserat.preis)
        formData.append('iban', inserat.iban)
        formData.append('kategorie', inserat.kategorie)
        formData.append('personId', inserat.personId)

        var config = {
            method: "post",
            url: api_root + "/api/inserat/inserieren",
            headers: {},
            data: formData,
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

    function uploadImage(e) {
        var image = e.target.files[0];
        var avatar;
            var reader = new FileReader();
            reader.readAsDataURL(image);
            reader.onload = e => {
                 avatar = e.target.result
            };
        
        formData.append('file', image);
    }
</script>

<div class="shadow-sm p-3 mb-5 bg-body rounded">
<h1 class="mt-3">Inserat erstellen</h1>
<form class="mb-5 form-group">
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
    </div>
    <div class="row mb-3 ">
        <label class="form-label" for="bild">Bild hochladen</label>
        <input bind:this={input} type="file" id="file" accept=".jpg, .jpeg, .png" on:change={(e)=>uploadImage(e)}/>
    </div>
    <div class="d-grid gap-2">
        <button type="button" class="btn btn-outline-secondary" on:click={inserieren}>Erstellen</button>
      </div>
    
</form>
</div>