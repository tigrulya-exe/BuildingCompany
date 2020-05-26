import React from 'react';
import {Col, Form} from 'react-bootstrap';
import AbstractFilter from '../AbstractFilter'

export default class LocksmithsFilter extends React.Component {
    constructor(props) {
        super(props);
        this.defaultState = {
            name: '',
            surname: '',
            patronymic: '',
            brigadeId: '',
            experienceYears: '',
            category: '',
            higherEducation: null
        };
        this.state = this.props.outerState || this.defaultState
    }

    render() {
        return (
            <AbstractFilter
                formId="technicalSpecialistsFilter"
                defaultState={this.defaultState}
                state={this.state}
                setState={(state) => this.setState(state)}
                onSubmit={this.props.onSubmit}>
                <Form.Row>
                    <Form.Group as={Col} controlId="name">
                        <Form.Label>Name</Form.Label>
                        <Form.Control value={this.state.name}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="surname">
                        <Form.Label>Surname</Form.Label>
                        <Form.Control value={this.state.surname}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="patronymic">
                        <Form.Label>Patronymic</Form.Label>
                        <Form.Control value={this.state.patronymic}/>
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="brigadeId">
                        <Form.Label>Brigade Id</Form.Label>
                        <Form.Control value={this.state.brigadeId}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="experienceYears">
                        <Form.Label>Experience Years</Form.Label>
                        <Form.Control value={this.state.experienceYears}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="category">
                        <Form.Label>Category</Form.Label>
                        <Form.Control value={this.state.category}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="higherEducation">
                        <Form.Check type="checkbox" value={this.state.higherEducation} label="Higher education"/>
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}