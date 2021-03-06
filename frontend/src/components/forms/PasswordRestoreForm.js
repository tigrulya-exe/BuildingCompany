import React from 'react';
import {Button, Col, Container, Form, Row} from 'react-bootstrap';
import {axiosNonApi} from '../../util/AxiosConfig'
import {Redirect} from "react-router-dom";
import {AuthContext} from "../../context/AuthContextProvider";
import ModalWindow from "../modals/Modal";


/**
 * Формочка логина(еще в разработке)
 * Если все гуд перекидывает на /themes
 */
export default class PasswordRestoreForm extends React.Component {
    static contextType = AuthContext;

    constructor(props) {
        super(props);
        this.state = {
            success: false,
            email: '',
            modalMessage: '',
            modalTitle: '',
            showModal: false,
            onModalClose: () => {
                this.setState({
                    showModal: false
                });
            }
        }
    }

    onModalClose = () => {
        this.setState({
            showModal: false
        });
    };

    onRestoreError = (error) => {
        this.setState({
            showModal: true,
            modalMessage: `Fix errors: ${(error.response && error.response.data.error) || 'Unknown error'}`,
            modalTitle: 'Error!'
        });
    };

    onFormChange = (event) => {
        this.setState({[event.target.id]: event.target.value});
    };

    handleResponse = (response) => {
        this.setState({
            showModal: true,
            modalMessage: `Follow the instructions in the message to reset your password!`,
            modalTitle: 'Success!',
            onModalClose: () => {
                this.setState({
                    showModal: false,
                    success: true
                });
            }
        });
    };

    onSubmit = (event) => {
        axiosNonApi.post('/restore', null, {params: {email: this.state.email}})
            .then((result) => this.handleResponse(result.data))
            .catch((error) => this.onRestoreError(error));
        event.preventDefault()
    };

    render() {
        if (this.state.success) {
            return (<Redirect to="/"/>)
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
                                <Form.Group controlId="email">
                                    <h1>
                                        <Form.Label>Restore password</Form.Label>
                                    </h1>
                                    <Form.Control type="email" placeholder="Enter your email"/>
                                </Form.Group>
                                <Form.Group>
                                    <Button variant="primary" className="float-right" type="submit">
                                        Submit
                                    </Button>
                                </Form.Group>
                            </Form>
                        </Col>
                    </Row>
                </Container>
            </>
        )
    }
}