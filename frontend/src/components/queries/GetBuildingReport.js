import React from 'react';
import {Form} from 'react-bootstrap';
import CollapseContainer from "../CollapseContainer";
import ModalWindow from "../modals/Modal";
import BuildingObjectsSingleSelectTable from "../readOnlyTables/BuildingObjectSingleSelectTable";
import WorkScheduleReadOnlyTable from "../readOnlyTables/WorkScheduleReadOnlyTable";


export default class GetBuildingReport extends React.Component {
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
                <WorkScheduleReadOnlyTable
                    url='/work-schedules/filter'
                    params={{
                        buildingObjectId: this.state.buildingObjectId,
                    }}/>
            </>
        )
    }
}