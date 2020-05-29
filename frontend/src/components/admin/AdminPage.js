import React from 'react';
import Nav from "react-bootstrap/Nav";
import {Link, useParams} from "react-router-dom";
import QueryForm from "./QueryForm";
import UsersTable from "./UsersTable";
import RolesTable from "./RolesTable";

const pageMap = new Map();

pageMap.set('query', <QueryForm/>);
pageMap.set('users', <UsersTable/>);
pageMap.set('roles', <RolesTable/>);

export default function AdminPage(props) {
    const {name} = useParams();
    return (
        <>
            <Nav variant="tabs" defaultActiveKey={name}>
                <Nav.Item>
                    <Nav.Link eventKey="query"><Link to="/admin/query">Native query</Link></Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="users"><Link to="/admin/users">Users</Link></Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="roles"><Link to="/admin/roles">Roles</Link></Nav.Link>
                </Nav.Item>

            </Nav>
            {pageMap.get(name)}
        </>
    )

}