import React from 'react';
import {Button, Col, Container, Form, Row} from 'react-bootstrap';
import {axiosNonApi} from '../../util/AxiosConfig'
import {Link, Redirect} from "react-router-dom";
import {AuthContext} from "../../context/AuthContextProvider";
import ModalWindow from "../modals/Modal";


/**
 * Формочка логина(еще в разработке)
 * Если все гуд перекидывает на /themes
 */
export default class Login extends React.Component {
    static contextType = AuthContext;

    constructor(props) {
        super(props);
        this.state = {
            login: '',
            password: '',
            modalMessage: '',
            modalTitle: '',
            showModal: false,
        }
    }

    onModalClose = () => {
        this.setState({
            showModal: false
        });
    };

    onLoginError = (error) => {
        this.setState({
            showModal: true,
            modalMessage: `Поправьте ошибки: ${(error.response && error.response.data.error) || 'Неизвестная ошибка'}`,
            modalTitle: 'Oшибка!'
        });
    };

    onFormChange = (event) => {
        this.setState({[event.target.id]: event.target.value});
    };

    handleAuthorize = (response) => {
        this.context.login(response)
    };

    onSubmit = (event) => {
        axiosNonApi.post('/sign-in', {login: this.state.login, password: this.state.password})
            .then((result) => this.handleAuthorize(result.data))
            .catch((error) => this.onLoginError(error));
        event.preventDefault()
    };

    render() {
        if (this.context.isAuthorized) {
            return (<Redirect to="/"/>)
        }

        return (
            <>
                <ModalWindow
                    show={this.state.showModal}
                    title={this.state.modalTitle}
                    message={this.state.modalMessage}
                    onModalClose={this.onModalClose}
                />
                <Container style={{height: '100vh'}}>
                    <Row style={{height: '100vh', alignItems: 'center', justifyContent: 'center'}}>
                        <Col md="auto">
                            <Form onChange={this.onFormChange} onSubmit={this.onSubmit}>
                                <Form.Group controlId="login">
                                    <h1>
                                        <Form.Label>Авторизация</Form.Label>
                                    </h1>
                                    <Form.Control placeholder="Введите ваше логин"/>
                                    <Form.Text className="text-muted">
                                        Ваше имя пользователя или пароль
                                    </Form.Text>
                                </Form.Group>

                                <Form.Group controlId="password">
                                    <Form.Label>Пароль</Form.Label>
                                    <Form.Control type="password" placeholder="Введите ваш пароль"/>
                                </Form.Group>
                                <Form.Group>
                                    <Button className="float-right" variant="primary" type="submit">
                                        Отправить
                                    </Button>
                                </Form.Group>
                                <Form.Group>
                                    <Button size='sm' variant="light">
                                        <Link to='/sign-up'>
                                            Нет аккаунта?
                                        </Link>
                                    </Button>
                                    <Button size='sm' variant="light">
                                        <Link to='/restore'>
                                            Забыли пароль?
                                        </Link>
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