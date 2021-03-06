import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import QueryForm from '../components/admin/QueryForm';
import CustomerTable from '../components/crudTables/CustomerTable';
import AreasTable from '../components/crudTables/AreasTable';
import MachineryTable from '../components/crudTables/MachineryTable';
import BrigadesTable from '../components/crudTables/BrigadesTable';
import ConstructionManagementTable from '../components/crudTables/ConstructionManagementTable';
import MaterialsTable from '../components/crudTables/MaterialsTable';
import OutlaysTable from '../components/crudTables/OutlaysTable';
import WorkScheduleTable from '../components/crudTables/WorkScheduleTable';
import Login from '../components/forms/Login';

import AuthenticatedRoute from "./AuthenticatedRoute";
import UnauthenticatedRoute from "./UnauthenticatedRoute";

import {Redirect, Switch,} from "react-router-dom";
import RegistrationForm from "../components/forms/RegistrationForm";
import PasswordRestoreForm from "../components/forms/PasswordRestoreForm";
import SpecialistsByAreaOrManagement from "../components/queries/SpecialistsByAreaOrManagement";
import ObjectsByAreaOrManagement from "../components/queries/ObjectsByAreaOrManagement";
import MachineryByBuildingObject from "../components/queries/MachineryByBuildingObject";
import TechicalSpecialistsPage from "../components/crudTables/technicalStuff/TechicalSpecialistsPage";
import WorkersPage from "../components/crudTables/workers/WorkersPage";
import BuildingObjectsPage from "../components/crudTables/buildingObjects/BuildingObjectsPage";
import WorkersByBuildingObject from "../components/queries/WorkersByBuildingObject";
import MachineryByManagements from "../components/queries/MachineryByManagements";
import Profile from "../components/Profile";
import AdminPage from "../components/admin/AdminPage";
import WorkTypesTable from "../components/crudTables/buildingObjects/WorkTypesTable";
import GetBuildingReport from "../components/queries/GetBuildingReport";
import ObjectsByWorkType from "../components/queries/ObjectsByWorkType";
import WorkTypesWithDelay from "../components/queries/WorkTypesWithDelay";
import MaterialsWithExceedance from "../components/queries/MaterialsWithExceedance";

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
                component={<TechicalSpecialistsPage/>}
                path="/technical-specialists/:name">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<WorkersPage/>}
                path="/workers/:name">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<BuildingObjectsPage/>}
                path="/building-objects/:name">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<SpecialistsByAreaOrManagement/>}
                path="/specialists-by-area-or-management">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<WorkersByBuildingObject/>}
                path="/workers-by-building-object">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<ObjectsByAreaOrManagement/>}
                path="/objects-by-area-or-management">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<MachineryByBuildingObject/>}
                path="/machinery-by-building-object">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<MachineryByManagements/>}
                path="/machinery-by-managements">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<WorkScheduleTable/>}
                path="/work-schedules">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<AdminPage/>}
                path="/admin/:name">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<Profile/>}
                path="/profile">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<WorkTypesTable/>}
                path="/work-types">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<ObjectsByWorkType/>}
                path="/objects-by-work-types">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<WorkTypesWithDelay/>}
                path="/work-types-with-delay">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<GetBuildingReport/>}
                path="/building-report">
            </AuthenticatedRoute>
            <AuthenticatedRoute
                component={<MaterialsWithExceedance/>}
                path="/materials-with-exceedance">
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
            <Redirect from='/' to='/profile'/>
        </Switch>
    );
}

