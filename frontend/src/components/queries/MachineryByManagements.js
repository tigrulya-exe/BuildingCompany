import React from 'react';
import {Form} from 'react-bootstrap';
import CollapseContainer from "../CollapseContainer";
import ManagementsSelectTable from "../readOnlyTables/ManagementsSelectTable";
import ModalWindow from "../modals/Modal";
import MachinerySelectTable from "../readOnlyTables/MachinerySelectTable";


export default class MachineryByManagements extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            constructionManagementIds: '',
            showResult: false,
            showModal: false,
            modalMessage: ''
        }
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
                    <Form.Group controlId="ManagementSelectForm">
                        <Form.Label>{`Selected management ids: ${this.state.constructionManagementIds}`}</Form.Label> <br/>
                        <CollapseContainer
                            buttonText="Expand managements"
                            childId="ManagementSelectTable">
                            <div id="ManagementSelectTable">
                                <ManagementsSelectTable
                                    onSelectSubmit={(data) => this.onTableSubmit(data, 'constructionManagementIds')}/>
                            </div>
                        </CollapseContainer>
                    </Form.Group>
                </Form>
                <MachinerySelectTable
                    url='/machinery/by-construction-managements'
                    params={{
                        constructionManagementIds: this.state.constructionManagementIds
                    }}/>
            </>
        )
    }
}