import React from 'react';
import {Button, Form, Col} from 'react-bootstrap';


export default class CustomersFilter extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            id: (this.props.outerState && this.props.outerState.id) || '',
            name: (this.props.outerState && this.props.outerState.name) ||  ''
        }
    }

    onIdChange = (event) => {
        this.setState({id: event.target.value});
    }

    onNameChange = (event) => {
        this.setState({name: event.target.value});
    }

    onSubmit = (event) => {
        this.props.onSubmit(this.state)
        event.preventDefault()
    }

    onReset = (event) => {
        this.setState({id: '', name: ''})
        this.props.onSubmit(null)
        event.preventDefault()
    }

    render() {
        return (
            <tr><td>
            <Form onSubmit={this.onSubmit}>
                <Form.Row>
                    {/* <Col>
                        <Form.Control placeholder="Id" value={this.state.id} onChange={this.onIdChange}/>
                    </Col> */}
                    <Col>
                        <Form.Control placeholder="Name" value={this.state.name} onChange={this.onNameChange}/>
                    </Col>
                    <Col>
                        <Button variant="primary" type="submit">Filter</Button>
                    </Col>
                    <Col>
                        <Button variant="primary" onClick={this.onReset}>Reset</Button>
                    </Col>
                </Form.Row>
            </Form>
            </td></tr>
        )
    }
}