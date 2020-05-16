import React from 'react';
import { Form, Col } from 'react-bootstrap';
import AbstractFilter from './AbstractFilter'

export default class TechnicalSpecialistsFilter extends React.Component {
    constructor(props) {
        super(props)
        this.defaultState = {
            name: '',
            surname: '',
            patronymic: '',
            educationalInstitution: '',
            areaId: '',
            managementId: '',
            experienceYears: '',
        }
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
                        <Form.Control value={this.state.name} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="surname">
                        <Form.Label>Surname</Form.Label>
                        <Form.Control value={this.state.surname} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="patronymic">
                        <Form.Label>Patronymic</Form.Label>
                        <Form.Control value={this.state.patronymic} />
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="educationalInstitution">
                        <Form.Label>Educational Institution</Form.Label>
                        <Form.Control value={this.state.educationalInstitution} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="areaId">
                        <Form.Label>Area Id</Form.Label>
                        <Form.Control value={this.state.areaId} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="managementId">
                        <Form.Label>Management Id</Form.Label>
                        <Form.Control value={this.state.managementId} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="experienceYears">
                        <Form.Label>Experience Years Id</Form.Label>
                        <Form.Control value={this.state.experienceYears} />
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}