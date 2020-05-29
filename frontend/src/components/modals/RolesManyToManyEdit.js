import React from "react";
import {AXIOS} from "../../util/AxiosConfig";
import ManyToManyEdit from "./ManyToManyEdit";
import RolesReadOnlyTable from "../readOnlyTables/RolesReadOnlyTable";

export default class RolesManyToManyEdit extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            roleIds: [],
        }
    }

    onSubmit = () => {
        if (!this.state.roleIds.length) {
            this.props.onModalClose();
            return;
        }

        AXIOS
            .post(`/users/${this.props.userId}/roles`, {
                ids: this.state.roleIds
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
                <RolesReadOnlyTable
                    onSelectSubmit={(data) => this.onTableSubmit(data, 'roleIds')}
                />
            </ManyToManyEdit>
        );
    }


}