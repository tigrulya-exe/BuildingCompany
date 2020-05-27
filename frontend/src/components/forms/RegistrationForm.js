import React from 'react';
import {Button, Col, Container, Form, Row} from 'react-bootstrap';
import {axiosNonApi} from '../../util/AxiosConfig'
import {Redirect} from "react-router-dom";
import {AuthContext} from "../../context/AuthContextProvider";
import ModalWindow from "../modals/Modal";

export default class RegistrationForm extends React.Component {
    static contextType = AuthContext;

    constructor(props) {
        super(props);
        this.state = {
            nickname: '',
            email: '',
            fullName: '',
            password: '',
            modalMessage: '',
            modalTitle: '',
            showModal: false,
            registered: false,
            onModalClose: () => {
                this.setState({
                    showModal: false
                });
            }
        }
    }

    onFormChange = (event) => {
        this.setState({[event.target.id]: event.target.value});
    };

    onRegistrationSuccess = () => {
        this.setState({
            showModal: true,
            modalMessage: 'It remains to confirm mail and sign in!',
            modalTitle: 'Success!',
            onModalClose: () => {
                this.setState({
                    showModal: false,
                    registered: true
                });
            }
        });
    };

    onRegistrationError = (error) => {
        this.setState({
            showModal: true,
            modalMessage: `Fix errors: ${(error.response && error.response.data.error) || 'Unknown error'}`,
            modalTitle: 'Error!'
        });
    };

    onSubmit = (event) => {
        axiosNonApi
            .post('/sign-up', this.state)
            .then((result) => this.onRegistrationSuccess())
            .catch((error) => this.onRegistrationError(error));
        event.preventDefault();
    };

    render() {
        if (this.context.isAuthorized) {
            return (<Redirect to="/"/>)
        }

        if (this.state.registered) {
            return (<Redirect to="/login"/>)
        }

        return (
            <>
                <ModalWindow
                    show={this.state.showModal}
                    title={this.state.modalTitle}
                    message={this.state.modalMessage}
                    onModalClose={this.state.onModalClose}
                />
                <Container style={{height: '100vh'}}>
                    <Row style={{height: '100vh', alignItems: 'center', justifyContent: 'center'}}>
                        <Col md="auto">
                            <Form onChange={this.onFormChange} onSubmit={this.onSubmit}>
                                <Form.Label>
                                    <h1> Registration </h1>
                                </Form.Label>
                                <br/>
                                <Form.Row>
                                    <Form.Group as={Col} controlId="nickname">
                                        <Form.Label>Nickname</Form.Label>
                                        <Form.Control placeholder="Enter your nickname"/>
                                    </Form.Group>

                                    <Form.Group as={Col} controlId="password">
                                        <Form.Label>Password</Form.Label>
                                        <Form.Control type="password" placeholder="Enter your password"/>
                                    </Form.Group>
                                </Form.Row>

                                <Form.Group controlId="fullName">
                                    <Form.Label>Полное имя</Form.Label>
                                    <Form.Control placeholder="e.g. Ivanov Ivan Ivanovich"/>
                                </Form.Group>

                                <Form.Group controlId="email">
                                    <Form.Label>Email</Form.Label>
                                    <Form.Control placeholder="Enter your email"/>
                                </Form.Group>

                                <Button variant="primary" className="float-right" type="submit">
                                    Submit
                                </Button>
                            </Form>
                        </Col>
                    </Row>
                </Container>
            </>
        )
    }
}