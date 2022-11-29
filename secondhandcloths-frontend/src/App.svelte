<script>
	import Router from "svelte-spa-router";
	import routes from "./routes";
	import { isAuthenticated, user } from "./store";
	import auth from "./auth.service";
</script>

<div id="app">
	<nav class="navbar navbar-expand-lg bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="#/">Second Hand Cloths</a>
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
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item">
						<a class="nav-link" aria-current="page" href="#/home"
							>Second Hand Cloths</a
						>
					</li>
				</ul>
			</div>
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
					<a class="nav-link" aria-current="page" href="#/account">Account</a>
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
