import React from "react";
import {Redirect, Route} from "react-router-dom";
import {AuthContext} from "../context/AuthContextProvider";
import history from "./History";

/**
 * Путь роутера для компонентов, которые должны быть доступны толко незареганным пользователям
 */

export default function UnauthenticatedRoute({component, ...rest}) {
    const isAuthenticated = React.useContext(AuthContext).isAuthorized;
    return (
        !isAuthenticated
            ? <Route history={history} {...rest}>{component}</Route>
            : <Redirect to="/"/>
    )
}