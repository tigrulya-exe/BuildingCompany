import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import QueryForm from '../QueryForm';
import CustomerTable from '../crudTables/CustomerTable';
import BuildingObjectsTable from '../crudTables/BuildingObjectsTable'
import AreasTable from '../crudTables/AreasTable';
import MachineryTable from '../crudTables/MachineryTable';
import BrigadesTable from '../crudTables/BrigadesTable';
import ConstructionManagementTable from '../crudTables/ConstructionManagementTable';
import MaterialsTable from '../crudTables/MaterialsTable';
import OutlaysTable from '../crudTables/OutlaysTable';
import TechnicalSpecialistsTable from '../crudTables/TechnicalSpecialistsTable';
import WorkScheduleTable from '../crudTables/WorkScheduleTable';
import Login from '../Login';

import AuthenticatedRoute from "./AuthenticatedRoute";
import UnauthenticatedRoute from "./UnauthenticatedRoute";

import {Switch,} from "react-router-dom";
import {AuthContext} from "../AuthProvider";

export default function RouteSwitch(props) {
    const isAuthenticated = React.useContext(AuthContext).isAuthorized;
    return (
        <Switch>
            <AuthenticatedRoute
                component={<CustomerTable/>}
                isAuthenticated={isAuthenticated}
                path="/customers">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<QueryForm/>}
                isAuthenticated={isAuthenticated} path="/query">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<BuildingObjectsTable/>}
                isAuthenticated={isAuthenticated} path="/building-objects">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<MachineryTable/>}
                isAuthenticated={isAuthenticated} path="/machinery">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<AreasTable/>}
                isAuthenticated={isAuthenticated} path="/areas">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<BrigadesTable/>}
                isAuthenticated={isAuthenticated} path="/brigades">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<ConstructionManagementTable/>}
                isAuthenticated={isAuthenticated} path="/construction-managements">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<MaterialsTable/>}
                isAuthenticated={isAuthenticated} path="/materials">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<OutlaysTable/>}
                isAuthenticated={isAuthenticated} path="/outlays">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<TechnicalSpecialistsTable/>}
                isAuthenticated={isAuthenticated} path="/technical-specialists">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<WorkScheduleTable/>}
                isAuthenticated={isAuthenticated} path="/work-schedules">
            </AuthenticatedRoute>
            <UnauthenticatedRoute
                component={<Login/>}
                isAuthenticated={isAuthenticated} path="/login">
            </UnauthenticatedRoute>
        </Switch>
    );
}

