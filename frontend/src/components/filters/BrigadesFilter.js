import React from 'react';
import { Form, Col } from 'react-bootstrap';
import AbstractFilter from './AbstractFilter'

export default class BrigadesFilter extends React.Component {
    constructor(props) {
        super(props)
        this.defaultState = {
            managerId: '',
        }
        this.state = this.props.outerState || this.defaultState
    }

    render() {
        return (
            <AbstractFilter
                formId="brigadesFilter"
                defaultState={this.defaultState}
                state={this.state}
                setState={(state) => this.setState(state)}
                onSubmit={this.props.onSubmit}>
                <Form.Row>
                    <Form.Group as={Col} controlId="managerId">
                        <Form.Label>Manager Id</Form.Label>
                        <Form.Control value={this.state.managerId} />
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}