import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import CustomerTable from './components/CustomerTable';
import QueryForm from './components/QueryForm';
import NavBar from './components/NavBar';

import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";

function App() {
  return (
    <Router>
      <NavBar />
      <Switch>
        <Route path="/customers">
          <CustomerTable />
        </Route>
        <Route path="/query">
          <QueryForm />
        </Route>
      </Switch>
    </Router>
  );
}

export default App;