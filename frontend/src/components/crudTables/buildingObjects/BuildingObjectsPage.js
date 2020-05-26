import React from 'react';
import Nav from "react-bootstrap/Nav";
import {
    Link,
    useParams
} from "react-router-dom";
import BuildingObjectsTable from "./BuildingObjectsTable";
import BridgesTable from "./BridgesTable";
import ResidentialHousesTable from "./ResidentialHousesTable";

const pageMap = new Map();

pageMap.set('all', <BuildingObjectsTable/>);
pageMap.set('bridges', <BridgesTable/>);
pageMap.set('residential-houses', <ResidentialHousesTable/>);

export default function BuildingObjectsPage(props) {
    const {name} = useParams();
    return (
        <>
            <Nav variant="tabs" defaultActiveKey={name}>
                <Nav.Item>
                    <Nav.Link eventKey="all"><Link to="/building-objects/all">All building objects</Link></Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="foremen"><Link to="/building-objects/bridges">Bridges</Link></Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="masters"><Link to="/building-objects/residential-houses">Residential Houses</Link></Nav.Link>
                </Nav.Item>
            </Nav>
            {pageMap.get(name)}
        </>
    )

}