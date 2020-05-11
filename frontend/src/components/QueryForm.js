import React from 'react';
import {Button, Form} from 'react-bootstrap';
import {AXIOS} from './http-common'


export default class QueryForm extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            query: '',
            result: ''
        }
    }

    onChange = (event) => {
        this.setState({query: event.target.value});
    }

    setResult(plainResults) {

        this.setState({
            result: plainResults.map(r => r.join('\t\t|\t\t')).join('\n')
        })
    }

    onSubmit = (event) => {
        AXIOS.get('/query', {params: {query: this.state.query}})
            .then((result) => this.setResult(result.data))
            .catch((error) => alert(error))
        event.preventDefault()
    }

    render() {
        return (
            <Form onSubmit={this.onSubmit}>
                <Form.Group controlId="exampleForm.ControlTextarea1">
                    <Form.Label>SQL query</Form.Label>
                    <Form.Control as="textarea" rows="8" value={this.state.query} onChange={this.onChange}/>
                </Form.Group>
                <Button variant="primary" type="submit">
                    Execute
                </Button>
                <Form.Group controlId="result">
                    <Form.Control readOnly as="textarea" rows="8" value={this.state.result}/>
                </Form.Group>
            </Form>
        )
    }
}