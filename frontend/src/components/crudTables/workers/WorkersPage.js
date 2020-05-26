import React from 'react';
import Nav from "react-bootstrap/Nav";
import {Link, useParams} from "react-router-dom";
import WorkersTable from "./WorkersTable";
import MasonsTable from "./MasonsTable";
import LocksmithsTable from "./LocksmithsTable";

const pageMap = new Map();

pageMap.set('all', <WorkersTable/>);
pageMap.set('masons', <MasonsTable/>);
pageMap.set('locksmiths', <LocksmithsTable/>);

export default function TechnicalSpecialistsPage(props) {
    const {name} = useParams();
    return (
        <>
            <Nav variant="tabs" defaultActiveKey={name}>
                <Nav.Item>
                    <Nav.Link eventKey="all"><Link to="/workers/all">All specialists</Link></Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="masons"><Link to="/workers/masons">Masons</Link></Nav.Link>
                </Nav.Item>
                <Nav.Item>
                    <Nav.Link eventKey="locksmiths"><Link to="/workers/locksmiths">Locksmiths</Link></Nav.Link>
                </Nav.Item>

            </Nav>
            {pageMap.get(name)}
        </>
    )

}