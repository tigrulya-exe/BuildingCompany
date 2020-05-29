import React from 'react';
import {Col, Form} from 'react-bootstrap';
import AbstractFilter from './AbstractFilter'


export default class RolesFilter extends React.Component {
    constructor(props) {
        super(props)
        this.defaultState = {
            role: '',
        }
        this.state = this.props.outerState || this.defaultState
    }

    render() {
        return (
            <AbstractFilter
                formId="materialsFilter"
                defaultState={this.defaultState}
                state={this.state}
                setState={(state) => this.setState(state)}
                onSubmit={this.props.onSubmit}>
                <Form.Row>
                    <Form.Group as={Col} controlId="role">
                        <Form.Label>Role name</Form.Label>
                        <Form.Control value={this.state.role}/>
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}