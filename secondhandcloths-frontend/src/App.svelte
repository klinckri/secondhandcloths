<script>
	import Router from "svelte-spa-router";
	import routes from "./routes";
	import { isAuthenticated, user } from "./store";
	import auth from "./auth.service";
</script>

<div id="app">
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#/">Home</a>
			<button
				class="navbar-toggler"
				type="button"
				data-bs-toggle="collapse"
				data-bs-target="#navbarNav"
				aria-controls="navbarNav"
				aria-expanded="false"
				aria-label="Toggle navigation"
			>
				<span class="navbar-toggler-icon" />
			</button>
			{#if $isAuthenticated}
				<div class="collapse navbar-collapse" id="navbarNav">
					<a
						class="btn btn-outline-primary"
						href="#/inseratErstellen"
						role="button">Inserat erstellen</a
					>
				</div>
			{/if}
			{#if $isAuthenticated}
				<div class="collapse navbar-collapse" id="navbarNav">
					<a class="nav-link" aria-current="page" href="#/account"
						>Account</a
					>
				</div>
			{/if}
			{#if $isAuthenticated}
				<div class="collapse navbar-collapse" id="navbarNav">
					<a class="nav-link" aria-current="page" href="#/warenkorb">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
							<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
						  </svg>Mein Warenkorb</a>
				</div>
			{/if}
			<div class="d-flex">
				{#if $isAuthenticated}
					<span class="navbar-text me-2"> {$user.name} </span>
					<button
						type="button"
						class="btn btn-primary"
						on:click={auth.logout}>Log Out</button
					>
				{:else}
					<button
						type="button"
						class="btn btn-primary"
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
