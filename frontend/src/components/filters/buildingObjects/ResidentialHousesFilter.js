import React from 'react';
import { Form, Col } from 'react-bootstrap';
import AbstractFilter from '../AbstractFilter'

export default class ResidentialHousesFilter extends React.Component {
    constructor(props) {
        super(props);
        this.defaultState = {
            customerId: '',
            areaId: '',
            name: '',
            minFlatCount: '',
            maxFlatCount: '',
            minFloorCount: '',
            maxFloorCount: '',
        };
        this.state = this.props.outerState || this.defaultState
    }

    render() {
        return (
            <AbstractFilter
                formId="buildinObjectsFilter"
                defaultState={this.defaultState}
                state={this.state}
                setState={(state) => this.setState(state)}
                onSubmit={this.props.onSubmit}>
                <Form.Row>
                    <Form.Group as={Col} controlId="customerId">
                        <Form.Label>Customer id</Form.Label>
                        <Form.Control type="number" value={this.state.customerId} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="name">
                        <Form.Label>Name</Form.Label>
                        <Form.Control value={this.state.name} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="areaId">
                        <Form.Label>Area Id</Form.Label>
                        <Form.Control type="number" value={this.state.areaId} />
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="minFlatCount">
                        <Form.Label>Min Flat Count</Form.Label>
                        <Form.Control type="number" value={this.state.minFlatCount} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="maxFlatCount">
                        <Form.Label>Max Flat Count</Form.Label>
                        <Form.Control type="number" value={this.state.maxFlatCount} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="minFloorCount">
                        <Form.Label>Min Floor Count</Form.Label>
                        <Form.Control type="number" value={this.state.minFloorCount} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="maxFloorCount">
                        <Form.Label> Max Floor Count</Form.Label>
                        <Form.Control type="number" value={this.state.maxFloorCount} />
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}