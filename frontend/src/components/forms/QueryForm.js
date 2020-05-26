import React from 'react';
import {Button, Col, Form, Jumbotron, Row} from 'react-bootstrap';
import {AXIOS} from '../../util/AxiosConfig'


export default class QueryForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            query: '',
            results: []
        }
    }

    onChange = (event) => {
        this.setState({query: event.target.value});
    };

    setResult(plainResults) {

        this.setState({
            results: plainResults
        })
    }

    getRow = (tuple) => {
        return (Array.isArray(tuple)
            && (<Row>{tuple && tuple.map(column => <Col sm style={{border: '1px solid'}}>{column}</Col>)}</Row>))
            || (<Row><Col sm style={{border: '1px solid'}}>{tuple}</Col></Row>)
    }

    getResults = () => {
        return this.state.results && this.state.results.map(tuple => this.getRow(tuple))
    }

    onSubmit = (event) => {
        AXIOS.get('/query', {params: {query: this.state.query}})
            .then((result) => this.setResult(result.data))
            .catch((error) => alert(error));
        event.preventDefault()
    };

    render() {
        return (
            <Form onSubmit={this.onSubmit}>
                <Form.Group controlId="exampleForm.ControlTextarea1">
                    <Form.Label>SQL query</Form.Label>
                    <Form.Control as="textarea" rows="8" value={this.state.query} onChange={this.onChange}/>
                </Form.Group>
                <Button variant="primary" className="float-right" type="submit">
                    Execute
                </Button>
                <br/>
                <br/>
                <Jumbotron>
                    {this.getResults()}
                </Jumbotron>
            </Form>
        )
    }
}