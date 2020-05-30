import React from 'react';
import {Col, Form} from 'react-bootstrap';
import CollapseContainer from "../CollapseContainer";
import ManagementsSelectTable from "../readOnlyTables/ManagementsSelectTable";
import ModalWindow from "../modals/Modal";
import BuildingObjectsSelectTable from "../readOnlyTables/BuldingObjectSelectTable";
import {DateTimePicker, MuiPickersUtilsProvider} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import WorkTypesReadOnlyTable from "../readOnlyTables/WorkTypesReadOnlyTable";


export default class ObjectsByWorkType extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            managementIds: '',
            showResult: false,
            showModal: false,
            modalMessage: '',
            workTypeIds: '',
            startDateMin: null,
            startDateMax: null
        }
    }

    onMaxChange = date => {
        this.setState({startDateMax: date})
    }

    onMinChange = date => {
        this.setState({startDateMin: date})
    }


    onTableSubmit = (data, propName) => {
        if (data) {
            this.setState({[propName]: this.arrayToParams(data)})
        }
    };

    arrayToParams = (array) => {
        return array.length ? array.reduce((f, s) => `${f},${s}`) : ''
    };

    onError = (responseBody) => {
        this.setState({
            showModal: true,
            modalMessage: responseBody.error || 'Unknown error'
        })
    };

    render() {
        return (
            <>
                <ModalWindow
                    show={this.state.showModal}
                    title='Error!'
                    message={this.state.modalMessage}
                    onModalClose={() => this.setState({showModal: false})}
                />
                <Form onSubmit={this.onSubmit}>
                    <Form.Row>
                        <Form.Group as={Col} controlId="startDateMin">
                            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                <DateTimePicker
                                    value={this.state.startDateMin}
                                    onChange={this.onMinChange}
                                    label="Min date"
                                    showTodayButton
                                    clearable
                                />
                            </MuiPickersUtilsProvider>
                        </Form.Group>
                        <Form.Group as={Col} controlId="startDateMax">
                            <MuiPickersUtilsProvider utils={DateFnsUtils}>
                                <DateTimePicker
                                    value={this.state.startDateMax}
                                    onChange={this.onMaxChange}
                                    label="Max date"
                                    showTodayButton
                                    clearable
                                />
                            </MuiPickersUtilsProvider>
                        </Form.Group>
                    </Form.Row>
                    <Form.Group controlId="WorkTypesSelectForm">
                        <Form.Label>{`Selected work types ids: ${this.state.workTypeIds}`}</Form.Label> <br/>
                        <CollapseContainer
                            buttonText="Expand Work Types"
                            childId="WorkTypesSelectTable">
                            <div id="WorkTypesSelectTable">
                                <WorkTypesReadOnlyTable
                                    onSelectSubmit={(data) => this.onTableSubmit(data, 'workTypeIds')}/>
                            </div>
                        </CollapseContainer>
                    </Form.Group>
                    <Form.Group controlId="ManagementSelectForm">
                        <Form.Label>{`Selected management ids: ${this.state.managementIds}`}</Form.Label> <br/>
                        <CollapseContainer
                            buttonText="Expand managements"
                            childId="ObjectManagementSelectTable">
                            <div id="ObjectManagementSelectTable">
                                <ManagementsSelectTable
                                    onSelectSubmit={(data) => this.onTableSubmit(data, 'managementIds')}/>
                            </div>
                        </CollapseContainer>
                    </Form.Group>
                </Form>
                <BuildingObjectsSelectTable
                    url='/building-objects/by-work-types'
                    params={{
                        workTypeIds: this.state.workTypeIds,
                        managementIds: this.state.managementIds,
                        startDateMin: this.state.startDateMin,
                        startDateMax: this.state.startDateMax
                    }}/>
            </>
        )
    }
}