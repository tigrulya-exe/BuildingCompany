import React from 'react';
import {Col, Form, Jumbotron, Navbar} from 'react-bootstrap';
import {axiosNonApi} from "../util/AxiosConfig";

export default class Profile extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            email: '',
            fullName: '',
            nickname: '',
        }
    }

    componentDidMount() {
        axiosNonApi.get('/profile')
            .then(response => {
                this.setState({
                    email: response.data.email,
                    fullName: response.data.fullName,
                    nickname: response.data.nickname,
                })
            })
            .catch(error => alert(error));
    }

    render() {
        return (
            <Jumbotron>
                <h1>Profile</h1>
                <Form.Group as={Col}>
                    <Form.Label>Nickname</Form.Label>
                    <Navbar bg="light">
                        <Navbar.Brand>{this.state.nickname}</Navbar.Brand>
                    </Navbar>
                </Form.Group>
                <Form.Group as={Col}>
                    <Form.Label>Full name</Form.Label>
                    <Navbar bg="light">
                        <Navbar.Brand>{this.state.fullName}</Navbar.Brand>
                    </Navbar>
                </Form.Group>
                <Form.Group as={Col}>
                    <Form.Label>Email</Form.Label>
                    <Navbar bg="light">
                        <Navbar.Brand>{this.state.email}</Navbar.Brand>
                    </Navbar>
                </Form.Group>
            </Jumbotron>
        );
    }
}
