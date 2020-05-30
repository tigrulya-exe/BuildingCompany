import {axiosNonApi} from "../util/AxiosConfig";

export default class TokenStorage {
    constructor(listeners) {
        this.isAuthorized = localStorage.getItem("refreshToken");
        this.listeners = listeners;
        this.alreadyRefreshed = false;
    }

    notifyListeners = () => {
        for (const listener of this.listeners) {
            listener(this.isAuthorized)
        }
    };

    setTokens = (tokens) => {
        localStorage.setItem('jwt', tokens.jwt);
        localStorage.setItem('refreshToken', tokens.refreshToken);
    };

    login = (tokens) => {
        this.setTokens(tokens);
        this.isAuthorized = true;
        this.alreadyRefreshed = false;
        this.notifyListeners()
    };

    logout = () => {
        localStorage.removeItem('jwt');
        localStorage.removeItem('refreshToken');
        this.isAuthorized = false;
        this.alreadyRefreshed = false;
        this.notifyListeners()
    };

    refreshTokens = () => {
        if (this.alreadyRefreshed) {
            this.logout();
            return;
        }
        this.alreadyRefreshed = true;
        axiosNonApi.post('/refresh', {token: localStorage.getItem("refreshToken")})
            .then(result => this.setTokens(result.data))
            .catch(error => this.logout())
    }
}