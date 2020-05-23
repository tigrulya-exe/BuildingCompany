import React from 'react';
import { Form, Col } from 'react-bootstrap';
import { AXIOS } from '../../util/AxiosConfig'

export default class ManagerInfo extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            id: this.props.id,
            name: '',
            surname: '',
            patronymic: '',
            educationalInstitution: '',
            experienceYears: ''
        }
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () =>
        new Promise((resolve, reject) => {
            AXIOS.get(`/technical-specialists/${this.state.id}`)
                .then((result) => this.setState(result.data))
                .catch((reason) => reject())
        })


    render() {
        return (
            <>
                <Form.Label>Manager info</Form.Label>
                <Form.Row>
                    <Form.Group as={Col} controlId="name">
                        <Form.Label>Name</Form.Label>
                        <Form.Control disabled value={this.state.name} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="surname">
                        <Form.Label>Surname</Form.Label>
                        <Form.Control disabled value={this.state.surname} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="patronymic">
                        <Form.Label>Patronymic</Form.Label>
                        <Form.Control disabled value={this.state.patronymic} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="educationalInstitution">
                        <Form.Label>Educational Institution</Form.Label>
                        <Form.Control disabled value={this.state.educationalInstitution} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="experienceYears">
                        <Form.Label>Experience Years</Form.Label>
                        <Form.Control disabled value={this.state.experienceYears} />
                    </Form.Group>
                </Form.Row>
            </>
        )
    }
}