import React from 'react';
import { Form, Col } from 'react-bootstrap';
import AbstractFilter from './AbstractFilter'
import DateTimePicker from 'react-datetime-picker';

export default class WorkScheduleFilter extends React.Component {
    constructor(props) {
        super(props)
        this.defaultState = {
            startDateMin: '',
            startDateMax: '',
            buildingObjectId: '',
            workTypeId: '',
            brigadeId: ''
        }
        this.state = this.props.outerState || this.defaultState
    }

    onMaxChange = date => {
        this.setState({startDateMax: date})
    }
    
    onMinChange = date => {
        this.setState({startDateMin: date})
    }

    render() {
        return (
            <AbstractFilter
                formId="workScheduleFilter"
                defaultState={this.defaultState}
                state={this.state}
                setState={(state) => this.setState(state)}
                onSubmit={this.props.onSubmit}>
                <Form.Row>
                    <Form.Group as={Col} controlId="startDateMin">
                        <Form.Label>Start date min</Form.Label>
                        <br></br>
                        <DateTimePicker
                            value={this.state.startDateMin}
                            onChange={this.onMinChange}
                        />
                    </Form.Group>
                    <Form.Group as={Col} controlId="startDateMax">
                        <Form.Label>Start date max</Form.Label>
                        <br></br>
                        <DateTimePicker
                            value={this.state.startDateMax}
                            onChange={this.onMaxChange}
                        />
                    </Form.Group>
                    <Form.Group as={Col} controlId="buildingObjectId">
                        <Form.Label>Building object id</Form.Label>
                        <Form.Control value={this.state.buildingObjectId} />
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="workTypeId">
                        <Form.Label>Work Type Id</Form.Label>
                        <Form.Control value={this.state.workTypeId} />
                    </Form.Group>
                    <Form.Group as={Col} controlId="brigadeId">
                        <Form.Label>Brigade Id</Form.Label>
                        <Form.Control value={this.state.brigadeId} />
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}