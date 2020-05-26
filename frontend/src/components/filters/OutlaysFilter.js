import React from 'react';
import {Col, Form} from 'react-bootstrap';
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
                        <Form.Control type="number" value={this.state.materialCountMin}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="materialCountMax">
                        <Form.Label>Material count max</Form.Label>
                        <Form.Control type="number" value={this.state.materialCountMax}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="materialId">
                        <Form.Label>Material Id</Form.Label>
                        <Form.Control type="number" value={this.state.materialId}/>
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}