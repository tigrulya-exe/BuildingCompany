import React from 'react';
import {Col, Form} from 'react-bootstrap';
import {AXIOS} from '../../util/AxiosConfig'

export default class ObjectScheduleInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            schedules: []
        }
    }

    getSchedules = () => {
        return this.state.schedules && this.state.schedules.map((schedule) =>
            <Form.Row>
                <Form.Group as={Col} controlId="startDate">
                    <Form.Label>Start date</Form.Label>
                    <Form.Control disabled value={schedule.startDate}/>
                </Form.Group>
                <Form.Group as={Col} controlId="endDate">
                    <Form.Label>End date</Form.Label>
                    <Form.Control disabled value={schedule.endDate}/>
                </Form.Group>
                <Form.Group as={Col} controlId="workType">
                    <Form.Label>Work type</Form.Label>
                    <Form.Control disabled value={schedule.workType}/>
                </Form.Group>
                <Form.Group as={Col} controlId="brigadeId">
                    <Form.Label>Brigade id</Form.Label>
                    <Form.Control disabled value={schedule.brigadeId}/>
                </Form.Group>
            </Form.Row>
        );
    };

    handleResults = (results) => {
        this.setState({
            schedules: results && results.content
        })
    };

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () =>
        new Promise((resolve, reject) => {
            AXIOS.get('/work-schedules/filter', {params: {buildingObjectId: this.props.objectId}})
                .then((result) => this.handleResults(result.data))
                .catch((reason) => reject())
        });


    render() {
        return (
            <>
                <h5><Form.Label>Schedule</Form.Label></h5>
                {this.getSchedules()}
            </>
        )
    }
}