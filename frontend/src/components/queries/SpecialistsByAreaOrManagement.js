import React from 'react';
import {Button, Form} from 'react-bootstrap';
import {AXIOS} from '../../util/AxiosConfig'
import CollapseContainer from "../CollapseContainer";
import AreaSelectTable from "../selectTables/AreaSelectTable";
import ManagementsSelectTable from "../selectTables/ManagementsSelectTable";


export default class SpecialistsByAreaOrManagement extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            areaIds: [],
            managementIds: [],
            showResult: false
        }
    }

    onTableSubmit = (data, propName) => {
        if(data){
            this.setState({[propName]: data.map(data => data.id)})
        }
    };


    onSubmit = (event) => {
        AXIOS.get('/query', {params: {query: this.state.query}})
            .then((result) => this.setResult(result.data))
            .catch((error) => alert(error))
        event.preventDefault()
    };

    render() {
        return (
            <Form onSubmit={this.onSubmit}>
                <Form.Group controlId="exampleForm.ControlTextarea1">
                    <Form.Label>Choose areas</Form.Label> <br/>
                    <CollapseContainer
                        buttonText="Expand areas"
                        childId="AreaSelectTable">
                        <div id="AreaSelectTable">
                            <AreaSelectTable onSelectSubmit={(data) => this.onTableSubmit(data, 'areaIds')}/>
                        </div>
                    </CollapseContainer>
                </Form.Group>
                <Form.Group controlId="exampleForm.ControlTextarea1">
                    <Form.Label>Choose managements</Form.Label> <br/>
                    <CollapseContainer
                        buttonText="Expand managements"
                        childId="ManagementSelectTable">
                        <div id="ManagementSelectTable">
                            <ManagementsSelectTable onSelectSubmit={(data) => this.onTableSubmit(data, 'managementIds')}/>
                        </div>
                    </CollapseContainer>
                </Form.Group>
                <Button variant="primary" className="float-right" type="submit">
                    Execute
                </Button>

            </Form>
        )
    }
}