import React from 'react';
import Nav from "react-bootstrap/Nav";
import TechnicalSpecialistsTable from "./TechnicalSpecialistsTable";
import {
    Link,
    useParams
} from "react-router-dom";
import ForemenTable from "./ForemenTable";
import MastersTable from "./MastersTable";

const pageMap = new Map();

pageMap.set('all', <TechnicalSpecialistsTable/>);
pageMap.set('foremen', <ForemenTable/>);
pageMap.set('masters', <MastersTable/>);

export default function TechnicalSpecialistsPage(props) {
    const {name} = useParams();
    return (
        <>
            <Nav variant="tabs" defaultActiveKey={name}>
                <Nav.Item>
                    <Nav.Link eventKey="all"><Link to="/technical-specialists/all">All specialists</Link></Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="foremen"><Link to="/technical-specialists/foremen">Foremen</Link></Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="masters"><Link to="/technical-specialists/masters">Masters</Link></Nav.Link>
                </Nav.Item>
            </Nav>
            {pageMap.get(name)}
        </>
    )

}