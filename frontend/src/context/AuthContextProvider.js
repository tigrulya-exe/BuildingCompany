import React from 'react'
import TokenStorage from "./TokenStorage";

const AuthContext = React.createContext();

/**
 * Глобальный контекст для опрделения авторизирован пользователь или нет
 */

let tokenStorage;

class AuthContextProvider extends React.Component {
    constructor(props) {
        super(props);
        tokenStorage = new TokenStorage([this.onTokensChange]);
        this.state = {
            isAuthorized: tokenStorage.isAuthorized,
        }
    }

    onTokensChange = (isAuthorized) => {
        this.setState({isAuthorized})
    };

    login = (tokens) => {
        tokenStorage.login(tokens);
    };

    logout = () => {
        tokenStorage.logout();
    };

    render() {
        return (
            <AuthContext.Provider value={{
                login: this.login,
                logout: this.logout,
                isAuthorized: this.state.isAuthorized
            }}>
                {this.props.children}
            </AuthContext.Provider>
        )
    }
}

export {AuthContextProvider, AuthContext, tokenStorage}