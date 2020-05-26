import React from 'react';
import {Col, Form} from 'react-bootstrap';
import AbstractFilter from './AbstractFilter'


export default class AreasFilter extends React.Component {
    constructor(props) {
        super(props)
        this.defaultState = {
            managementId: '',
            managerId: '',
        }
        this.state = this.props.outerState || this.defaultState
    }

    render() {
        return (
            <AbstractFilter
                formId="areasFilter"
                defaultState={this.defaultState}
                state={this.state}
                setState={(state) => this.setState(state)}
                onSubmit={this.props.onSubmit}>
                <Form.Row>
                    <Form.Group as={Col} controlId="managementId">
                        <Form.Label>Management Id</Form.Label>
                        <Form.Control type="number" value={this.state.managementId}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="managerId">
                        <Form.Label>Manager Id</Form.Label>
                        <Form.Control type="number" value={this.state.managerId}/>
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}