import React from "react";
import {Redirect, Route} from "react-router-dom";

export default function UnauthenticatedRoute({component, isAuthenticated, ...rest}) {
    return (
        !isAuthenticated
            ? <Route {...rest}>{component}</Route>
            : <Redirect to="/"/>
    )
}
