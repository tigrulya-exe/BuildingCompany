import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import QueryForm from './QueryForm';
import CustomerTable from './crudTables/CustomerTable';
import BuildingObjectsTable from './crudTables/BuildingObjectsTable'
import AreasTable from './crudTables/AreasTable';
import MachineryTable from './crudTables/MachineryTable';
import BrigadesTable from './crudTables/BrigadesTable';

import {Route, Switch,} from "react-router-dom";

export default class RouteSwitch extends React.Component {
    render() {
        return (
            <Switch>
                <Route path="/customers">
                    <CustomerTable/>
                </Route>
                <Route path="/building-objects">
                    <BuildingObjectsTable/>
                </Route>
                <Route path="/query">
                    <QueryForm/>
                </Route>
                <Route path="/machinery">
                    <MachineryTable/>
                </Route>
                <Route path="/areas">
                    <AreasTable/>
                </Route>
                <Route path="/brigades">
                    <BrigadesTable/>
                </Route>
            </Switch>
        );
    }
}