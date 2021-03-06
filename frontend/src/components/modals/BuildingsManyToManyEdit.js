import React from "react";
import WorkTypesReadOnlyTable from "../readOnlyTables/WorkTypesReadOnlyTable";
import {AXIOS} from "../../util/AxiosConfig";
import ManyToManyEdit from "./ManyToManyEdit";

export default class BuildingsManyToManyEdit extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            workTypesIds: [],
        }
    }

    onSubmit = () => {
        if (!this.state.workTypesIds.length) {
            this.props.onModalClose();
            return;
        }

        AXIOS
            .post(`/building-objects/workTypes/${this.props.buildingObjectId}`, {
                ids: this.state.workTypesIds
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
                <WorkTypesReadOnlyTable
                    onSelectSubmit={(data) => this.onTableSubmit(data, 'workTypesIds')}
                />
            </ManyToManyEdit>
        );
    }


}