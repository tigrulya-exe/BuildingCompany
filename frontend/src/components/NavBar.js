import React from 'react';
import {Navbar, Nav, NavDropdown, Form, Button, FormControl} from 'react-bootstrap';

import {
    Link
  } from "react-router-dom";

export default class NavBar extends React.Component {
    render() {
        return (
            <Navbar bg="dark" variant="dark" expand="lg">
                <Navbar.Brand>Building Company</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link><Link to="/query">Native query</Link></Nav.Link>
                        <NavDropdown title="Tables" id="basic-nav-dropdown">
                            <NavDropdown.Item><Link to="/customers">Customers</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/building-objects">Building Objects</Link></NavDropdown.Item>
                            <NavDropdown.Item><Link to="/machinery">Machinery</Link></NavDropdown.Item>                            
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
        )
    }
}