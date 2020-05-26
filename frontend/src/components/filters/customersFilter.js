import React from 'react';
import {Col, Form} from 'react-bootstrap';
import AbstractFilter from './AbstractFilter'

export default class CustomersFilter extends React.Component {
    constructor(props) {
        super(props)
        this.state = this.props.outerState || {
            id: '',
            name: '',
        }
    }

    render() {
        return (
            <AbstractFilter
                formId="customersFilter"
                defaultState={this.defaultState}
                state={this.state}
                setState={(state) => this.setState(state)}
                onSubmit={this.props.onSubmit}>
                <Form.Row>
                    <Form.Group as={Col} controlId="name">
                        <Form.Label>Name</Form.Label>
                        <Form.Control value={this.state.name}/>
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}