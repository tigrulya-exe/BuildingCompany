import React from 'react';
import {Button, Col, Form, Jumbotron, Row} from 'react-bootstrap';
import {AXIOS} from '../../util/AxiosConfig'
import ReactPaginate from 'react-paginate';

export default class QueryForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            query: '',
            results: [],
            perPage: 5,
            currentPage: 0,
            offset: 0,
            totalCount: 0
        }
    }

    handlePageClick = (data) => {
        const selectedPage = data.selected;
        const offset = selectedPage * this.state.perPage;

        this.setState({
            currentPage: selectedPage,
            offset: offset,
            results: this.state.results
        }, () => this.onSubmit());

    };

    onChange = (event) => {
        this.setState({query: event.target.value});
    };

    setResult(plainResults) {
        this.setState({
            results: plainResults?.result,
            totalCount: plainResults?.totalCount
        })
    }

    getRow = (tuple) => {
        return (Array.isArray(tuple)
            && (<Row>{tuple && tuple.map(column => <Col sm><Form.Control disabled value={column}/></Col>)}</Row>))
            || (<Row><Col sm><Form.Control disabled value={tuple}/></Col></Row>)
    };

    getResults = () => {
        // const data = this.state.results?.slice(this.state.offset, this.state.offset + this.state.perPage);
        const data = this.state.results;
        return data?.map(tuple => this.getRow(tuple))
    };

    onSubmit = (event) => {
        AXIOS.get('/query', {
            params: {
                query: this.state.query,
                pageSize: this.state.perPage,
                page: this.state.currentPage
            }
        })
            .then((result) => this.setResult(result.data))
            .catch((error) => alert(error));
        event && event.preventDefault()
    };

    getPagination = () => {
        if (this.state.results.length)
            return <ReactPaginate
                previousLabel={'previous'}
                nextLabel={'next'}
                breakLabel={'...'}
                breakClassName={'break-me'}
                pageCount={Math.ceil(this.state.totalCount / this.state.perPage)}
                onPageChange={this.handlePageClick}
                marginPagesDisplayed={2}
                pageRangeDisplayed={5}
                containerClassName={'pagination'}
                subContainerClassName={'pages pagination'}
                activeClassName={'active'}
            />
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
                {this.getPagination()}
            </Form>
        )
    }
}