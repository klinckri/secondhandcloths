import { writable, derived } from "svelte/store";

export const isAuthenticated = writable(false);
export const user = writable({});
export const jwt_token = writable([]);
export const anzahlInserate = writable(0);

