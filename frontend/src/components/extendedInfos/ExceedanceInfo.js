import React from 'react';
import {Col, Form} from 'react-bootstrap';
import {AXIOS} from '../../util/AxiosConfig'

export default class ExceedanceInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            outlayId: this.props.outlayId,
            count: null
        }
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () =>
        new Promise((resolve, reject) => {
            AXIOS.get(`/outlays/by-outlay/${this.state.outlayId}`)
                .then((result) => this.setState({count: result.data.count}))
                .catch((reason) => reject())
        });

    render() {
        return (this.state.count) ?
            (<>
                <Form.Label>Exceedance</Form.Label>
                <Form.Row>
                    <Form.Group as={Col} controlId="count">
                        <Form.Control disabled value={this.state.count}/>
                    </Form.Group>
                </Form.Row>
            </>) : null;
    }
}