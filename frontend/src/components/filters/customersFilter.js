import React from 'react';
import { Button, Form, Col } from 'react-bootstrap';
import CollapseContainer from "../CollapseContainer"


export default class CustomersFilter extends React.Component {
    constructor(props) {
        super(props)
        this.state = this.props.outerState || {
            id: '',
            name: '',
        }
    }

    onFormChange = (event) => {
        this.setState({ [event.target.id]: event.target.value });
    }

    onSubmit = (event) => {
        this.props.onSubmit(this.state)
        event.preventDefault()
    }

    onReset = (event) => {
        this.setState({ id: '', name: '' })
        this.props.onSubmit(null)
        event.preventDefault()
    }

    render() {
        return (
            <tr><td>
                <CollapseContainer>
                    <Form onSubmit={this.onSubmit} onChange={this.onFormChange}>
                        <Form.Row>
                            <Form.Group as={Col} controlId="name">
                                <Form.Label>Name</Form.Label>
                                <Form.Control value={this.state.name} />
                            </Form.Group>

                        </Form.Row>
                        <Form.Row>
                            <Button variant="primary" type="submit">Filter</Button>
                            <Button variant="danger" onClick={this.onReset}>Reset</Button>
                        </Form.Row>
                    </Form>
                </CollapseContainer>
            </td></tr>
        )
    }
}