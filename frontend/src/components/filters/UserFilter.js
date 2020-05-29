import React from 'react';
import {Col, Form} from 'react-bootstrap';
import AbstractFilter from './AbstractFilter'

export default class UsersFilter extends React.Component {
    constructor(props) {
        super(props)
        this.state = this.props.outerState || {
            id: '',
            nickname: '',
            email: '',
            fullName: ''
        }
    }

    render() {
        return (
            <AbstractFilter
                formId="usersFilter"
                defaultState={this.defaultState}
                state={this.state}
                setState={(state) => this.setState(state)}
                onSubmit={this.props.onSubmit}>
                <Form.Row>
                    <Form.Group as={Col} controlId="nickname">
                        <Form.Label>Nickname</Form.Label>
                        <Form.Control value={this.state.nickname}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="email">
                        <Form.Label>Email</Form.Label>
                        <Form.Control value={this.state.email}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="fullName">
                        <Form.Label>FullName</Form.Label>
                        <Form.Control value={this.state.fullName}/>
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}