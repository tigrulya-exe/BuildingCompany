import 'bootstrap/dist/css/bootstrap.min.css';

import React from 'react';
import RouteSwitch from './components/RouteSwitch';
import NavBar from './components/NavBar';

import {
  BrowserRouter as Router,
} from "react-router-dom";

function App() {
  return (
    <Router>
      <NavBar />
      <RouteSwitch/>
    </Router>
  );
}

export default App;