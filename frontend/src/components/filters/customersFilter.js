import React from 'react';
import {Button, Form, Col} from 'react-bootstrap';
import {AXIOS} from './http-common'


export default class CustomersFilter extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            id: '',
            name: ''
        }
    }

    onIdChange = (event) => {
        this.setState({id: event.target.value});
    }

    onNameChange = (event) => {
        this.setState({name: event.target.value});
    }

    onSubmit = (event) => {
        AXIOS.get('/query', {params: {query: this.state.query}})
            .then((result) => this.setResult(result.data))
            .catch((error) => alert(error))
        event.preventDefault()
    }

    render() {
        return (
            <Form onSubmit={() => this.props.onSubmit(this.state)}>
                <Form.Row>
                    <Col>
                        <Form.Control placeholder="Id" value={this.state.id} onChange={this.onIdChange}/>
                    </Col>
                    <Col>
                        <Form.Control placeholder="Name" value={this.state.name} onChange={this.onNameChange}/>
                    </Col>
                    <Col>
                        <Button variant="primary" type="submit">Filter</Button>
                    </Col>
                </Form.Row>
            </Form>
        )
    }
}