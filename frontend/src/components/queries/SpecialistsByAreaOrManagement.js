import React from 'react';
import {Form} from 'react-bootstrap';
import CollapseContainer from "../CollapseContainer";
import AreaSelectTable from "../readOnlyTables/AreaSelectTable";
import ManagementsSelectTable from "../readOnlyTables/ManagementsSelectTable";
import ModalWindow from "../modals/Modal";
import TechnicalSpecialistsSelectTable from "../readOnlyTables/TechnicalSpecialistsSelectTable";


export default class SpecialistsByAreaOrManagement extends React.Component {
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
        return array.length ? array.reduce((f, s) => `${f},${s}`) : array
    };

    onError = (responseBody) => {
        this.setState({
            showModal: true,
            modalMessage: responseBody.error || 'Unknown error'
        })
    };

    // TODO передать через пропсы и возвращать бул
    // onSubmit = (event) => {
    //     event.preventDefault();
    //
    //     if (!this.state.areaIds.length && !this.state.managementIds.length) {
    //         this.setState({
    //             showModal: true,
    //             modalMessage: 'Choose areas or/and managements!'
    //         });
    //     }
    //
    //     this.setState({})
    // };

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
                    <Form.Group controlId="exampleForm.ControlTextarea1">
                        <Form.Label>{`Selected area ids: ${this.state.areaIds}`}</Form.Label> <br/>
                        <CollapseContainer
                            buttonText="Expand areas"
                            childId="AreaSelectTable">
                            <div id="AreaSelectTable">
                                <AreaSelectTable onSelectSubmit={(data) => this.onTableSubmit(data, 'areaIds')}/>
                            </div>
                        </CollapseContainer>
                    </Form.Group>
                    <Form.Group controlId="exampleForm.ControlTextarea1">
                        <Form.Label>{`Selected management ids: ${this.state.managementIds}`}</Form.Label> <br/>
                        <CollapseContainer
                            buttonText="Expand managements"
                            childId="ManagementSelectTable">
                            <div id="ManagementSelectTable">
                                <ManagementsSelectTable
                                    onSelectSubmit={(data) => this.onTableSubmit(data, 'managementIds')}/>
                            </div>
                        </CollapseContainer>
                    </Form.Group>
                </Form>
                <TechnicalSpecialistsSelectTable
                    url='/technical-specialists/by-areas-or-managements'
                    params={{
                        areaIds: this.state.areaIds,
                        managementIds: this.state.managementIds
                    }}/>
            </>
        )
    }
}