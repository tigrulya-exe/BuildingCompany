import React from 'react';
import {Col, Form} from 'react-bootstrap';
import CollapseContainer from "../CollapseContainer";
import ModalWindow from "../modals/Modal";
import BuildingObjectsSingleSelectTable from "../readOnlyTables/BuildingObjectSingleSelectTable";
import MachinerySelectTable from "../readOnlyTables/MachinerySelectTable";
import {DateTimePicker, MuiPickersUtilsProvider} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";


export default class ObjectsByAreaOrManagement extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            buildingObjectId: null,
            showModal: false,
            modalMessage: '',
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
        this.setState({[propName]: data})
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
                    <Form.Group controlId="BuildingObjectSelectForm">
                        <Form.Label>{`Selected building object id: ${this.state.buildingObjectId}`}</Form.Label> <br/>
                        <CollapseContainer
                            buttonText="Expand areas"
                            childId="ObjectAreaSelectTable">
                            <div id="ObjectAreaSelectTable">
                                <BuildingObjectsSingleSelectTable
                                    onSelectSubmit={(data) => this.onTableSubmit(data, 'buildingObjectId')}/>
                            </div>
                        </CollapseContainer>
                    </Form.Group>
                </Form>
                <MachinerySelectTable
                    url='/machinery/by-building-object'
                    params={{
                        buildingObjectId: this.state.buildingObjectId,
                        startDateMin: this.state.startDateMin,
                        startDateMax: this.state.startDateMax
                    }}/>
            </>
        )
    }
}