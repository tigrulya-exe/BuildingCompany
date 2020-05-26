import React from 'react';
import {Button, Form, Nav, Navbar, NavDropdown} from 'react-bootstrap';

import {Link} from "react-router-dom";
import {AuthContext} from "../context/AuthContextProvider";
import history from "../routing/History";

export default class NavBar extends React.Component {
    static contextType = AuthContext;

    onSignIn = () => {
        history.push('/login');
    };

    onLogout = () => {
        history.push('/login');
        this.context.logout();
    };

    onSignUp = () => {
        history.push('/sign-up');
    };

    render() {
        return (
            <Navbar bg="dark" variant="dark" expand="lg">
                <Navbar.Brand>Building Company</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link><Link to="/query">Native query</Link></Nav.Link>
                        <NavDropdown title="Tables" id="basic-nav-dropdown">
                            <NavDropdown.Item><Link to="/customers">Customers</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/building-objects/all">Building Objects</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/machinery">Machinery</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/areas">Areas</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/brigades">Brigades</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/construction-managements">Construction
                                Managements</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/materials">Materials</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/outlays">Outlays</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/technical-specialists/all">Technical
                                Specialists</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/workers/all">Workers</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/work-schedules">Work Schedules</Link></NavDropdown.Item>
                        </NavDropdown>
                        <NavDropdown title="Queries" id="basic-nav-dropdown">
                            <NavDropdown.Item><Link
                                to="/specialists-by-area-or-management">SpecialistsByAreaOrManagement</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/objects-by-area-or-management">ObjectsByAreaOrManagement</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link
                                to="/machinery-by-building-objects">MachineryByBuildingObjects</Link></NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                    {
                        this.context.isAuthorized
                            ? <Form inline>
                                <Button variant="danger" onClick={this.onLogout}>Logout</Button>
                            </Form>
                            : <Form inline>
                                <Button variant="light" onClick={this.onSignIn}>Sign In</Button>
                                <Button variant="primary" onClick={this.onSignUp}>Sign Up</Button>
                            </Form>
                    }

                </Navbar.Collapse>
            </Navbar>
        )
    }
}