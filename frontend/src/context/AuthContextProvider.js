import React from 'react'

const AuthContext = React.createContext();

/**
 * Глобальный контекст для опрделения авторизирован пользователь или нет
 */

class AuthContextProvider extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isAuthorized: localStorage.getItem("refreshToken")
        }
    }

    login = (tokens) => {
        localStorage.setItem('jwt', tokens.jwt);
        localStorage.setItem('refreshToken', tokens.refreshToken)
        this.setState({isAuthorized: true});
    };

    logout = () => {
        localStorage.removeItem('jwt');
        localStorage.removeItem('refreshToken');
        this.setState({isAuthorized: false})
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

export {AuthContextProvider, AuthContext}