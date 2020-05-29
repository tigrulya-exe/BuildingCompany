import React from 'react';
import {AXIOS} from "../../util/AxiosConfig";
import RolesManyToManyEdit from "../modals/RolesManyToManyEdit";
import RolesReadOnlyTable from "../readOnlyTables/RolesReadOnlyTable";

export default class RolesInfo extends React.Component {
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
            AXIOS.delete(`users/${this.props.userId}/roles/${oldData.id}`)
                .catch((reason) =>
                    alert(`Error deleting entity: ${this.getErrorFromReason(reason)}`)
                );

            resolve()
        });

    render() {
        return (
            <>
                <RolesManyToManyEdit
                    show={this.state.showModal}
                    onModalClose={this.onModalClose}
                    title="Many to many edit"
                    userId={this.props.userId}
                />

                <RolesReadOnlyTable
                    onDelete={this.onDelete}
                    setRefresh={(refresh) => this.setState({refresh})}
                    params={{
                        userId: this.props.userId
                    }}
                    url={'/roles/by-user'}
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