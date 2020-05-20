import {Route, Redirect} from "react-router-dom";
import React from "react";

export default function AuthenticatedRoute({ component, isAuthenticated , ...rest }) {
    return (
        isAuthenticated
            ? <Route {...rest}>{component}</Route>
            : <Redirect to="/login"/>
        // <Route
        //     {...rest}
        //     render={
        //         props => {
        //             console.log(isAuthenticated)
        //             return isAuthenticated
        //                 ? <component {...props} />
        //                 : <Redirect to="/login"/>
        //         }
        //
        //     }
        // />
    );
}
