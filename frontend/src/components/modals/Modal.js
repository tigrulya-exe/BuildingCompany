import {Button, Modal} from "react-bootstrap";
import React from "react";

export default function ModalWindow(props) {

    return (
        <Modal
            aria-labelledby="contained-modal-title-vcenter"
            centered
            onHide={props.onModalClose}
            show={props.show}>
            <Modal.Header closeButton>
                <Modal.Title>{props.title}</Modal.Title>
            </Modal.Header>
            <Modal.Body>{props.message}</Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={props.onModalClose}>
                    OK
                </Button>
            </Modal.Footer>
        </Modal>
    );
}