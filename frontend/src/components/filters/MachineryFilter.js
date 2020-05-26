import React from 'react';
import { Form, Col } from 'react-bootstrap';
import AbstractFilter from './AbstractFilter'

export default class MachineryFilter extends React.Component {
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

    render() {
        return (
            <AbstractFilter
                formId="machineryFilter"
                defaultState={this.defaultState}
                state={this.state}
                setState={(state) => this.setState(state)}
                onSubmit={this.props.onSubmit}>
                <Form.Row>
                    <Form.Group as={Col} controlId="buildingObjectId">
                        <Form.Label>Building object id</Form.Label>
                        <Form.Control type="number" value={this.state.buildingObjectId} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="managementId">
                        <Form.Label>Management Id</Form.Label>
                        <Form.Control type="number" value={this.state.managementId} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="areaId">
                        <Form.Label>Area Id</Form.Label>
                        <Form.Control type="number" value={this.state.areaId} />
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
            </AbstractFilter>
        )
    }
}