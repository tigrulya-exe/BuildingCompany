import 'bootstrap/dist/css/bootstrap.min.css';

import React from 'react';
import RouteSwitch from './components/routing/RouteSwitch';
import NavBar from './components/NavBar';

import {BrowserRouter as Router,} from "react-router-dom";
import {AuthProvider} from "./components/AuthProvider";

function App() {
    return (
        <Router>
            <AuthProvider>
                <NavBar/>
                <RouteSwitch/>
            </AuthProvider>
        </Router>
    );
}

export default App;