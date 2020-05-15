import React from 'react';
import { Form, Col } from 'react-bootstrap';
import AbstractFilter from './AbstractFilter'

export default class OutlaysFilter extends React.Component {
    constructor(props) {
        super(props)
        this.defaultState = {
            materialCountMin: '',
            materialCountMax: '',
            materialId: '',
            buildingObjectId: '',
            workTypeId: '',
            brigadeId: ''
        }
        this.state = this.props.outerState || this.defaultState
    }

    render() {
        return (
            <AbstractFilter
                formId="outlaysFilter"
                defaultState={this.defaultState}
                state={this.state}
                setState={(state) => this.setState(state)}
                onSubmit={this.props.onSubmit}>
                <Form.Row>
                    <Form.Group as={Col} controlId="materialCountMin">
                        <Form.Label>Material count min</Form.Label>
                        <Form.Control value={this.state.materialCountMin} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="materialCountMax">
                        <Form.Label>Material count max</Form.Label>
                        <Form.Control value={this.state.materialCountMax} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="materialId">
                        <Form.Label>Material Id</Form.Label>
                        <Form.Control value={this.state.materialId} />
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="buildingObjectId">
                        <Form.Label>Building object id</Form.Label>
                        <Form.Control value={this.state.buildingObjectId} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="workTypeId">
                        <Form.Label>Work Type Id</Form.Label>
                        <Form.Control value={this.state.workTypeId} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="brigadeId">
                        <Form.Label>Brigade Id</Form.Label>
                        <Form.Control value={this.state.brigadeId} />
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}