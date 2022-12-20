<script>
	import Router from "svelte-spa-router";
	import routes from "./routes";
	import { isAuthenticated, user, anzahlInserate} from "./store";
	import auth from "./auth.service";
</script>

<div id="app">
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid d-grid gap-2 d-md-flex">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">

			<li><a class="navbar-brand float-left" href="#/">Home</a></li>
			{#if $isAuthenticated}
					<li><a class="nav-link float-left" aria-current="page" href="#/account">Account</a></li>
			{/if}
			{#if $isAuthenticated}
					<li><a class="nav-link float-left" aria-current="page" href="#/inseratErstellen">Inserat erstellen</a></li>
			{/if}
			{#if $isAuthenticated}
					<li><a class="nav-link float-left" aria-current="page" href="#/warenkorb">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
							<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
						  </svg> Mein Warenkorb <span class="badge bg-secondary">{$anzahlInserate}</span></a></li>
			{/if}
			{#if $isAuthenticated}
			<li><a class="nav-link float-left" href="#/weather">Wetterprognoze</a></li>
			{/if}
			</ul>
			<div class="d-flex float-end">
				{#if $isAuthenticated}
					<span class="navbar-text me-2"> {$user.name} </span>
					<button
						type="button"
						class="btn btn-outline-secondary"
						on:click={auth.logout}>Log Out</button
					>
				{:else}
					<button
						type="button"
						class="btn btn-outline-secondary"
						on:click={auth.loginWithPopup}>Log In</button
					>
				{/if}
			</div>
		</div>
	</nav>

	<div class="container">
		<Router {routes} />
	</div>
</div>
