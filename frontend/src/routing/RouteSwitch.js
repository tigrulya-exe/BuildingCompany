import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import QueryForm from '../components/forms/QueryForm';
import CustomerTable from '../components/crudTables/CustomerTable';
import BuildingObjectsTable from '../components/crudTables/BuildingObjectsTable'
import AreasTable from '../components/crudTables/AreasTable';
import MachineryTable from '../components/crudTables/MachineryTable';
import BrigadesTable from '../components/crudTables/BrigadesTable';
import ConstructionManagementTable from '../components/crudTables/ConstructionManagementTable';
import MaterialsTable from '../components/crudTables/MaterialsTable';
import OutlaysTable from '../components/crudTables/OutlaysTable';
import TechnicalSpecialistsTable from '../components/crudTables/TechnicalSpecialistsTable';
import WorkScheduleTable from '../components/crudTables/WorkScheduleTable';
import Login from '../components/forms/Login';

import AuthenticatedRoute from "./AuthenticatedRoute";
import UnauthenticatedRoute from "./UnauthenticatedRoute";

import {Switch,} from "react-router-dom";
import RegistrationForm from "../components/forms/RegistrationForm";
import PasswordRestoreForm from "../components/forms/PasswordRestoreForm";
import SpecialistsByAreaOrManagement from "../components/queries/SpecialistsByAreaOrManagement";

export default function RouteSwitch(props) {
    return (
        <Switch>
            <AuthenticatedRoute
                component={<CustomerTable/>}
                path="/customers">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<QueryForm/>}
                path="/query">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<BuildingObjectsTable/>}
                path="/building-objects">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<MachineryTable/>}
                path="/machinery">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<AreasTable/>}
                path="/areas">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<BrigadesTable/>}
                path="/brigades">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<ConstructionManagementTable/>}
                path="/construction-managements">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<MaterialsTable/>}
                path="/materials">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<OutlaysTable/>}
                path="/outlays">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<TechnicalSpecialistsTable/>}
                path="/technical-specialists">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<SpecialistsByAreaOrManagement/>}
                path="/specialists-by-area-or-management">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<WorkScheduleTable/>}
                path="/work-schedules">
            </AuthenticatedRoute>
            <UnauthenticatedRoute
                component={<Login/>}
                path="/login">
            </UnauthenticatedRoute>
            <UnauthenticatedRoute
                component={<RegistrationForm/>}
                path="/sign-up">
            </UnauthenticatedRoute>
            <UnauthenticatedRoute
                component={<PasswordRestoreForm/>}
                path="/restore">
            </UnauthenticatedRoute>
        </Switch>
    );
}

