import React from 'react';
import {Col, Form} from 'react-bootstrap';
import {AXIOS} from '../../util/AxiosConfig'

export default class DelayInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            scheduleId: this.props.scheduleId,
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
                        <Form.Control type="date" disabled value={this.state.name}/>
                    </Form.Group>
                </Form.Row>
            </>
        )
    }
}