import React from 'react';
import {Col, Form} from 'react-bootstrap';
import AbstractFilter from './AbstractFilter'
import {DateTimePicker, MuiPickersUtilsProvider} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";

export default class WorkScheduleFilter extends React.Component {
    constructor(props) {
        super(props)
        this.defaultState = {
            startDateMin: null,
            startDateMax: null,
            buildingObjectId: '',
            workType: '',
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
                        <MuiPickersUtilsProvider utils={DateFnsUtils}>
                            <DateTimePicker
                                value={this.state.startDateMin}
                                onChange={this.onMinChange}
                                label="Min date"
                                clearable
                                showTodayButton
                            />
                        </MuiPickersUtilsProvider>
                    </Form.Group>
                    <Form.Group as={Col} controlId="startDateMax">
                        <MuiPickersUtilsProvider utils={DateFnsUtils}>
                            <DateTimePicker
                                value={this.state.startDateMax}
                                onChange={this.onMaxChange}
                                label="Max date"
                                clearable
                                showTodayButton
                            />
                        </MuiPickersUtilsProvider>
                    </Form.Group>
                    <Form.Group as={Col} controlId="buildingObjectId">
                        <Form.Label>Building object id</Form.Label>
                        <Form.Control type="number" value={this.state.buildingObjectId}/>
                    </Form.Group>
                </Form.Row>
                <Form.Row>
                    <Form.Group as={Col} controlId="workType">
                        <Form.Label>Work Type Id</Form.Label>
                        <Form.Control value={this.state.workType}/>
                    </Form.Group>
                    <Form.Group as={Col} controlId="brigadeId">
                        <Form.Label>Brigade Id</Form.Label>
                        <Form.Control type="number" value={this.state.brigadeId}/>
                    </Form.Group>
                </Form.Row>
            </AbstractFilter>
        )
    }
}