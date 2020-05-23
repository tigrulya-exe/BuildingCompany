import {Route, Redirect} from "react-router-dom";
import React from "react";
import {AuthContext} from "../context/AuthContextProvider";
import history from "./History";

/**
 * Путь роутера для компонентов, которые должны быть доступны толко зареганным пользователям
 */

export default function AuthenticatedRoute({ component, ...rest }) {
    const isAuthenticated = React.useContext(AuthContext).isAuthorized;
    return (
        isAuthenticated ?
            <Route history={history} {...rest}>{component}</Route>
            : <Redirect to="/login"/>
    );
}
