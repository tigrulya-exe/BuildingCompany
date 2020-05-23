import 'bootstrap/dist/css/bootstrap.min.css';

import React from 'react';
import RouteSwitch from './routing/RouteSwitch';
import NavBar from './components/NavBar';
import history from "./routing/History";

import {BrowserRouter as Router,} from "react-router-dom";
import {AuthContextProvider} from "./context/AuthContextProvider";

function App() {
    return (
        <Router history={history}>
            <AuthContextProvider>
                <NavBar/>
                <RouteSwitch/>
            </AuthContextProvider>
        </Router>
    );
}

export default App;