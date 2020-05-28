import React from 'react';
import WorkTypesReadOnlyTable from "../readOnlyTables/WorkTypesReadOnlyTable";
import BuildingsManyToManyEdit from "../modals/BuildingsManyToManyEdit";


export default class WorkTypesInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            workTypesIds: [],
            showModal: false,
        }
    }

    onModalClose = () => {
        this.setState({
            showModal: false
        })
    };

    showModal = () => {
        this.setState({
            showModal: true
        });
    };


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