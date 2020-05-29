import React from 'react';
import {AXIOS} from "../../util/AxiosConfig";
import PermissionsSelectTable from "../readOnlyTables/PermissionsSelectTable";
import PermissionsManyToManyEdit from "../modals/PermissionsManyToManyEdit";

export default class PermissionsInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
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
            AXIOS.delete(`roles/${this.props.roleId}/permissions/${oldData.id}`)
                .catch((reason) =>
                    alert(`Error deleting entity: ${this.getErrorFromReason(reason)}`)
                );

            resolve()
        });

    render() {
        return (
            <>
                <PermissionsManyToManyEdit
                    show={this.state.showModal}
                    onModalClose={this.onModalClose}
                    title="Many to many edit"
                    roleId={this.props.roleId}
                />

                <PermissionsSelectTable
                    onDelete={this.onDelete}
                    setRefresh={(refresh) => this.setState({refresh})}
                    params={{
                        roleId: this.props.roleId
                    }}
                    url={'/permissions/by-role'}
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