import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import CustomerTable from './CustomerTable';
import BuildingObjectsTable from './BuildingObjectsTable'
import QueryForm from './QueryForm';
import MachineryTable from './MachineryTable';

import {
  Switch,
  Route,
} from "react-router-dom";

export default class RouteSwitch extends React.Component{
    render(){
        return (   
            <Switch>
              <Route path="/customers">
                <CustomerTable />
              </Route>
              <Route path="/building-objects">
                <BuildingObjectsTable />
              </Route>
              <Route path="/query">
                <QueryForm />
              </Route>
              <Route path="/machinery">
                <MachineryTable />
              </Route>
            </Switch>
        );
    }
}