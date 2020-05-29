import React from "react";
import {AXIOS} from "../../util/AxiosConfig";
import ManyToManyEdit from "./ManyToManyEdit";
import PermissionsSelectTable from "../readOnlyTables/PermissionsSelectTable";

export default class PermissionsManyToManyEdit extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            permissionIds: [],
        }
    }

    onSubmit = () => {
        if (!this.state.permissionIds.length) {
            this.props.onModalClose();
            return;
        }

        AXIOS
            .post(`/roles/${this.props.roleId}/permissions`, {
                ids: this.state.permissionIds
            })
            .then(response => {
                alert('Success');
                this.props.onModalClose()
            })
            .catch(error => alert('Error'))
    };


    onTableSubmit = (data, propName) => {
        if (data) {
            this.setState({[propName]: data})
        }
    };


    render() {
        return (
            <ManyToManyEdit
                {...this.props}
                onSubmit={this.onSubmit}
            >
                <PermissionsSelectTable
                    onSelectSubmit={(data) => this.onTableSubmit(data, 'permissionIds')}
                />
            </ManyToManyEdit>
        );
    }

}