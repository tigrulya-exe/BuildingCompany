import React from 'react';
import { Form, Col } from 'react-bootstrap';
import AbstractFilter from './AbstractFilter'


export default class MaterialsFilter extends React.Component {
    constructor(props) {
        super(props)
        this.defaultState = {
            name: '',
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
                    <Form.Group as={Col} controlId="name">
                        <Form.Label>Name</Form.Label>
                        <Form.Control value={this.state.name} />
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}