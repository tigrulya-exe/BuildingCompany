import React from 'react';
import WorkTypesReadOnlyTable from "../readOnlyTables/WorkTypesReadOnlyTable";
import BuildingsManyToManyEdit from "../modals/BuildingsManyToManyEdit";
import {AXIOS} from "../../util/AxiosConfig";


export default class WorkTypesInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            workTypesIds: [],
            showModal: false,
            refresh: null
        };
    }

    onModalClose = () => {
        this.setState({
            showModal: false
        });
        this.state.refresh()
    };

    showModal = () => {
        this.setState({
            showModal: true
        });
    };

    getErrorFromReason = (reason) => reason.response?.data?.error;

    onDelete = (oldData) =>
        new Promise((resolve, reject) => {
            AXIOS.delete(`building-objects/${this.props.buildingObjectId}/workTypes/${oldData.id}`)
                .then((result) => resolve())
                .catch((reason) => {
                        alert(`Error deleting entity: ${this.getErrorFromReason(reason)}`);
                        resolve()
                    }
                );

        });

    render() {
        return (
            <>
                <BuildingsManyToManyEdit
                    show={this.state.showModal}
                    onModalClose={this.onModalClose}
                    title="Many to many edit"
                    buildingObjectId={this.props.buildingObjectId}
                />
                <WorkTypesReadOnlyTable
                    onDelete={this.onDelete}
                    setRefresh={(refresh) => this.setState({refresh})}
                    params={{
                        buildingObjectId: this.props.buildingObjectId
                    }}
                    url={'/work-types/by-building-object'}
                    action={{
                        icon: 'add',
                        tooltip: 'Add entities',
                        isFreeAction: true,
                        onClick: this.showModal,
                    }}
                />
            </>
        )
    }
}