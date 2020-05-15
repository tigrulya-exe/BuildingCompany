import React from 'react';
import { Button, Form, Col } from 'react-bootstrap';
import CollapseContainer from "../CollapseContainer"

export default class CustomersFilter extends React.Component {
    constructor(props) {
        super(props)
        this.defaultState = {
            buildingObjectId: '',
            managementId: '',
            areaId: '',
            type: '',
            licencePlateNumber: ''
        }
        this.state = this.props.outerState || this.defaultState
    }

    onFormChange = (event) => {
        this.setState({ [event.target.id]: event.target.value });
    }

    onSubmit = (event) => {
        this.props.onSubmit(this.state)
        event.preventDefault()
    }

    onReset = (event) => {
        this.setState(this.defaultState)
        this.props.onSubmit(null)
        event.preventDefault()
    }

    render() {
        return (
            <tr><td colSpan="4">
                <CollapseContainer childId="machineryFilter">
                    <Form onSubmit={this.onSubmit} id="machineryFilter" onChange={this.onFormChange}>
                        <Form.Row>
                            <Form.Group as={Col} controlId="buildingObjectId">
                                <Form.Label>Building object id</Form.Label>
                                <Form.Control value={this.state.buildingObjectId} />
                            </Form.Group>
                            <Form.Group as={Col} controlId="managementId">
                                <Form.Label>Management Id</Form.Label>
                                <Form.Control value={this.state.managementId} />
                            </Form.Group>
                            <Form.Group as={Col} controlId="areaId">
                                <Form.Label>Area Id</Form.Label>
                                <Form.Control value={this.state.areaId} />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="type">
                                <Form.Label>Type</Form.Label>
                                <Form.Control value={this.state.type} />
                            </Form.Group>
                            <Form.Group as={Col} controlId="licencePlateNumber">
                                <Form.Label>License plate number</Form.Label>
                                <Form.Control value={this.state.licencePlateNumber} />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Col>
                                <Button variant="primary" type="submit">Filter</Button>
                                <Button variant="danger" onClick={this.onReset}>Reset</Button>
                            </Col>
                        </Form.Row>
                    </Form>
                </CollapseContainer>
            </td></tr>
        )
    }
}