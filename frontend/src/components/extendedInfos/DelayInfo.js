import React from 'react';
import {Col, Form} from 'react-bootstrap';
import {AXIOS} from '../../util/AxiosConfig'

export default class DelayInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            scheduleId: this.props.scheduleId,
            delay: null
        }
    }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () =>
        new Promise((resolve, reject) => {
            AXIOS.get(`/work-schedules/by-schedule-row/${this.state.scheduleId}`)
                .then((result) => this.setState({delay: result.data.delay}))
                .catch((reason) => reject())
        });

    render() {
        return (this.state.delay) ?
            (<>
                <Form.Label>Delay</Form.Label>
                <Form.Row>
                    <Form.Group as={Col} controlId="name">
                        <Form.Control disabled value={new Date(this.state.delay).toLocaleTimeString()}/>
                    </Form.Group>
                </Form.Row>
            </>) : null;
    }
}