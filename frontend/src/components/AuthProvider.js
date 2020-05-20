import React from 'react'

const AuthContext = React.createContext();

class AuthProvider extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            isAuthorized: localStorage.getItem("refreshToken")
        }
    }

    setAuthorizedOuter = (value) => {
        this.setState({isAuthorized: value})
    };

    render(){
        return (
            <AuthContext.Provider value={{
                isAuthorized: this.state.isAuthorized,
                setAuthorizedOuter: this.setAuthorizedOuter
            }}>{this.props.children}</AuthContext.Provider>
        )
    }
}
export {AuthProvider, AuthContext}