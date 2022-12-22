import createAuth0Client from "@auth0/auth0-spa-js";
import { user, isAuthenticated, jwt_token, anzahlInserate } from "./store";
import config from "./auth.config";

let auth0Client;
const api_root = window.location.origin;

async function createClient() {
  auth0Client = await createAuth0Client({
    domain: config.domain,
    client_id: config.clientId
  });
}

async function loginWithPopup() {
  try {
    await createClient();
    await auth0Client.loginWithPopup();
    user.set(await auth0Client.getUser());
    const claims = await auth0Client.getIdTokenClaims();
    const id_token = await claims.__raw;
    jwt_token.set(id_token);
    console.log(id_token);
    isAuthenticated.set(true);
    getWarenkorbSize();
  } catch (e) {
    console.error(e);
  } 
}

function getWarenkorbSize() {
  var config = {
      method: "get",
      url: api_root + "/api/kauf/warenkorbSize",
      headers: { Authorization: "Bearer " + jwt_token },
  };

  axios(config)
      .then(function (response) {
          anzahlInserate.set(response.data);
      })
      .catch(function (error) {
          console.log(error);
      });
}

function logout() {
  return auth0Client.logout();
}

const auth = {
  createClient,
  loginWithPopup,
  logout
};

export default auth;