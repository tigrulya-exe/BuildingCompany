import React from 'react';
import {Button, Form} from 'react-bootstrap';
import CollapseContainer from "../CollapseContainer";
import ModalWindow from "../modals/Modal";
import BuildingObjectsSingleSelectTable from "../readOnlyTables/BuildingObjectSingleSelectTable";
import WorkScheduleReadOnlyTable from "../readOnlyTables/WorkScheduleReadOnlyTable";
import OutlaysSelectTable from "../readOnlyTables/OutlaysSelectTable";


export default class GetBuildingReport extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            buildingObjectId: null,
            showModal: false,
            modalMessage: '',
            startDateMin: null,
            startDateMax: null,
            scheduleRefresh: null,
            outlayRefresh: null
        }
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

    onRefresh = () => {
        this.state.scheduleRefresh();
        this.state.outlayRefresh();
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
                    <Form.Group controlId="BuildingObjectSelectForm">
                        <Form.Label>{`Selected building object id: ${this.state.buildingObjectId}`}</Form.Label> <br/>
                        <CollapseContainer
                            buttonText="Expand building objects"
                            childId="BuildingObjectsSingleSelectTable">
                            <div id="BuildingObjectsSingleSelectTable">
                                <BuildingObjectsSingleSelectTable
                                    onSelectSubmit={(data) => this.onTableSubmit(data, 'buildingObjectId')}/>
                            </div>
                        </CollapseContainer>
                    </Form.Group>
                </Form>
                <Button onClick={this.onRefresh}>Submit</Button>
                <WorkScheduleReadOnlyTable
                    url='/work-schedules/filter'
                    setRefresh={(refresh) => this.setState({scheduleRefresh: refresh})}
                    params={{
                        buildingObjectId: this.state.buildingObjectId,
                    }}/>
                <OutlaysSelectTable
                    url='/outlays/filter'
                    setRefresh={(refresh) => this.setState({outlayRefresh: refresh})}
                    params={{
                        buildingObjectId: this.state.buildingObjectId,
                    }}
                />
            </>
        )
    }
}