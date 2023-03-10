
import Home from "./pages/Home.svelte";
import InseratErstellen from "./pages/InseratErstellen.svelte";
import Account from "./pages/Account.svelte";
import Warenkorb from "./pages/Warenkorb.svelte";
import Weather from "./pages/Weather.svelte";

export default {
    '/': Home,
    '/home': Home,
    '/inseratErstellen': InseratErstellen,
    '/account': Account,
    '/warenkorb': Warenkorb,
    '/weather': Weather,
}