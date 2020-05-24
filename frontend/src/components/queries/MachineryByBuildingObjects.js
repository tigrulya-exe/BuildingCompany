import React from 'react';
import {Form} from 'react-bootstrap';
import CollapseContainer from "../CollapseContainer";
import ModalWindow from "../modals/Modal";
import BuildingObjectsSelectTable from "../readOnlyTables/BuldingObjectSelectTable";


export default class ObjectsByAreaOrManagement extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            areaIds: '',
            managementIds: '',
            showResult: false,
            showModal: false,
            modalMessage: ''
        }
    }

    onTableSubmit = (data, propName) => {
        if (data) {
            this.setState({[propName]: this.arrayToParams(data.map(data => data.id))})
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
                    <Form.Group controlId="BuildingObjectSelectForm">
                        <Form.Label>{`Selected building object ids: ${this.state.areaIds}`}</Form.Label> <br/>
                        <CollapseContainer
                            buttonText="Expand areas"
                            childId="ObjectAreaSelectTable">
                            <div id="ObjectAreaSelectTable">
                                <BuildingObjectsSelectTable
                                    onSelectSubmit={(data) => this.onTableSubmit(data, 'areaIds')}/>
                            </div>
                        </CollapseContainer>
                    </Form.Group>
                </Form>
                <BuildingObjectsSelectTable
                    url='/building-objects/by-areas-or-managements'
                    params={{
                        areaIds: this.state.areaIds,
                        managementIds: this.state.managementIds
                    }}/>
            </>
        )
    }
}