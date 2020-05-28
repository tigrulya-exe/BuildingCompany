import {Button, Modal} from "react-bootstrap";
import React from "react";
import WorkTypesReadOnlyTable from "../readOnlyTables/WorkTypesReadOnlyTable";
import {AXIOS} from "../../util/AxiosConfig";

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

    getIds = (objects) => objects?.map(ob => ob.id);

    onTableSubmit = (data, propName) => {
        if (data) {
            this.setState({[propName]: data})
        }
    };

    render() {
        return (
            <Modal
                aria-labelledby="contained-modal-title-vcenter"
                centered
                onHide={this.props.onModalClose}
                show={this.props.show}>
                <Modal.Header closeButton>
                    <Modal.Title>{this.props.title}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <WorkTypesReadOnlyTable
                        onSelectSubmit={(data) => this.onTableSubmit(data, 'workTypesIds')}
                    />
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={this.onSubmit}>
                        Submit
                    </Button>
                    <Button variant="secondary" onClick={this.props.onModalClose}>
                        Cancel
                    </Button>
                </Modal.Footer>
            </Modal>
        );
    }


}