import {Button, Modal} from "react-bootstrap";
import React from "react";

export default class ManyToManyEdit extends React.Component {
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
                    {this.props.children}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={this.props.onSubmit}>
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