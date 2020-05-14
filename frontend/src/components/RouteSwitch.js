import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import QueryForm from './QueryForm';
import CustomerTable from './crudTables/CustomerTable';
import BuildingObjectsTable from './crudTables/BuildingObjectsTable'
import AreasTable from './crudTables/AreasTable';
import MachineryTable from './crudTables/MachineryTable';
import BrigadesTable from './crudTables/BrigadesTable';
import ConstructionManagementTable from './crudTables/ConstructionManagementTable';
import MaterialsTable from './crudTables/MaterialsTable';
import OutlaysTable from './crudTables/OutlaysTable';
import TechnicalSpecialistsTable from './crudTables/TechnicalSpecialistsTable';
import WorkScheduleTable from './crudTables/WorkScheduleTable';


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
                <Route path="/construction-managements">
                    <ConstructionManagementTable/>
                </Route>
                <Route path="/materials">
                    <MaterialsTable/>
                </Route>
                <Route path="/outlays">
                    <OutlaysTable/>
                </Route>
                <Route path="/technical-specialists">
                    <TechnicalSpecialistsTable/>
                </Route>
                <Route path="/work-schedules">
                    <WorkScheduleTable/>
                </Route>
            </Switch>
        );
    }
}