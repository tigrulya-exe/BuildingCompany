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
            modalMessage: 'Осталось подтвердить почту и авторизоваться',
            modalTitle: 'Успешно!',
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
            modalMessage: `Поправьте ошибки: ${error.response.data.error || 'Неизвестная ошибка'}`,
            modalTitle: 'Oшибка!'
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
                                    <h1> Регистрация </h1>
                                </Form.Label>
                                <br/>
                                <Form.Row>
                                    <Form.Group as={Col} controlId="nickname">
                                        <Form.Label>Имя пользователя</Form.Label>
                                        <Form.Control placeholder="Введите ваш логин"/>
                                    </Form.Group>

                                    <Form.Group as={Col} controlId="password">
                                        <Form.Label>Пароль</Form.Label>
                                        <Form.Control type="password" placeholder="Введите ваш пароль"/>
                                    </Form.Group>
                                </Form.Row>

                                <Form.Group controlId="fullName">
                                    <Form.Label>Полное имя</Form.Label>
                                    <Form.Control placeholder="Например: Иванов Иван Иванович"/>
                                </Form.Group>

                                <Form.Group controlId="email">
                                    <Form.Label>Почта</Form.Label>
                                    <Form.Control placeholder="Введите вашу почту"/>
                                </Form.Group>

                                <Button variant="primary" type="submit">
                                    Отправить
                                </Button>
                            </Form>
                        </Col>
                    </Row>
                </Container>
            </>
        )
    }
}