const APPLICATION_PATH = "/";
module.exports = Object.freeze({
    AUTH_MODE: window.Configs.AUTH_MODE || "Keycloak",
    ROOT_PATH: window.Configs.ROOT_PATH || APPLICATION_PATH,
    ACTIVE_LAYOUT: window.Configs.ACTIVE_LAYOUT || "layout1", //layout1 = vertical, layout2=horizontal
    API_ENPOINT: window.Configs.API_ENPOINT || "https://localhost:8071", //local
    LOGOUT_URL: window.Configs.LOGOUT_URL,
    REDIRECT_URL: window.Configs.REDIRECT_URL,
    REQUIRED_ROLE: window.Configs.REQUIRED_ROLE,
    //  API_ENPOINT: "https://newvitimes.globits.net:8092",
    LOGIN_PAGE: APPLICATION_PATH + "session/signin", //Nếu là Spring
    HOME_PAGE: APPLICATION_PATH + "dashboard", //Nếu là Spring
});