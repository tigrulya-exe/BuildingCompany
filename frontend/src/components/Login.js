import React from 'react';
import { Button, Form } from 'react-bootstrap';
import { axiosNonApi } from './http-common'
import {Redirect} from "react-router-dom";


export default class QueryForm extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            login: '',
            password: '',
            redirect: false
        }
    }

    onFormChange = (event) => {
        this.setState({ [event.target.id]: event.target.value });
    }

    onSubmit = (event) => {
        axiosNonApi.post('/sign-in', {login: this.state.login, password: this.state.password})
            .then((result) => this.setState({redirect: true}))
            .catch((error) => alert(error.response.data.error))
        event.preventDefault()
    }

    render() {
        if(this.state.redirect){
            return (<Redirect to="/customers" />)
        }

        return (
            <Form onChange={this.onFormChange} onSubmit={this.onSubmit}>
                <Form.Group controlId="login" >
                    <Form.Label>Login</Form.Label>
                    <Form.Control placeholder="Enter login" />
                    <Form.Text className="text-muted">
                        Your nickname or email.
                    </Form.Text>
                </Form.Group>

                <Form.Group controlId="password">
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" placeholder="Password" />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        )
    }
}