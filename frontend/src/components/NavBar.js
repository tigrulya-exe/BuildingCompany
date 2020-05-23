import React from 'react';
import {Button, Form, FormControl, Nav, Navbar, NavDropdown} from 'react-bootstrap';

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
                            <NavDropdown.Item><Link to="/building-objects">Building Objects</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/machinery">Machinery</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/areas">Areas</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/brigades">Brigades</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/construction-managements">Construction Managements</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/materials">Materials</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/outlays">Outlays</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/technical-specialists">Technical Specialists</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/work-schedules">Work Schedules</Link></NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                    {
                        this.context.isAuthorized
                            ? <Form inline>
                                <Button variant="outline-primary" onClick={this.onLogout}>Logout</Button>
                            </Form>
                            : <Form inline>
                                <Button variant="outline-primary" onClick={this.onSignIn}>Sign In</Button>
                                <Button variant="primary" onClick={this.onSignUp}>Sign Up</Button>
                            </Form>
                    }

                </Navbar.Collapse>
            </Navbar>
        )
    }
}