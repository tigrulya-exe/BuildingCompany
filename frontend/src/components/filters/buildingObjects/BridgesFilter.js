import React from 'react';
import {Col, Form} from 'react-bootstrap';
import AbstractFilter from '../AbstractFilter'

export default class BridgesFilter extends React.Component {
    constructor(props) {
        super(props);
        this.defaultState = {
            customerId: '',
            areaId: '',
            name: '',
            minWidthInMetres: '',
            maxWidthInMetres: '',
            minNumberOfTrafficLanes: '',
            maxNumberOfTrafficLanes: '',
            typeOfSpan: ''
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
                        <Form.Control type="number" value={this.state.customerId}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="name">
                        <Form.Label>Name</Form.Label>
                        <Form.Control value={this.state.name}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="areaId">
                        <Form.Label>Area Id</Form.Label>
                        <Form.Control type="number" value={this.state.areaId}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="typeOfSpan">
                        <Form.Label>Type of span</Form.Label>
                        <Form.Control value={this.state.typeOfSpan}/>
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="minWidthInMetres">
                        <Form.Label>Min Width In Metres</Form.Label>
                        <Form.Control type="number" value={this.state.minWidthInMetres}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="maxWidthInMetres">
                        <Form.Label>Max Width In Metres</Form.Label>
                        <Form.Control type="number" value={this.state.maxWidthInMetres}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="minNumberOfTrafficLanes">
                        <Form.Label>Min Number Of TrafficLanes</Form.Label>
                        <Form.Control type="number" value={this.state.minNumberOfTrafficLanes}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="maxNumberOfTrafficLanes">
                        <Form.Label>Max Number Of TrafficLanes</Form.Label>
                        <Form.Control type="number" value={this.state.maxNumberOfTrafficLanes}/>
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}