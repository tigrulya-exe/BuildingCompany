import React from 'react';
import MaterialTable from 'material-table';
import { AXIOS } from './http-common'

export default class CrudTable extends React.Component {
    constructor(props) {
        super(props)
        this.refreshEntities()
        this.state = {
            data: [],
            infoMessage: '',
            showMessage: false
        }
    }

    showMessage(text){
        this.setState({
            infoMessage: text,
            showMessage: true
        })

        setInterval(() => this.setState({showMessage: false}), 6000)
    }

    refreshEntities = () => {
        AXIOS.get(`/${this.props.entityName}s`)
            .then(response => {
                this.setState({ data: response.data.content })
            })
            .catch((reason) => this.showMessage(`Error refreshing ${this.props.entityName}s: ${reason}`))
    }

    addEntity = newData =>
        new Promise(resolve => {
            AXIOS.post(`/${this.props.entityName}s`, newData)
                .then(() => this.showMessage(`${this.props.entityName} was added`))
                .catch((reason) => this.showMessage(`Error adding ${this.props.entityName}: ${reason}`))
                .finally(() => this.refreshEntities())
            resolve()
        })

    updateEntity = (newData, oldData) =>
        new Promise((resolve, reject) => {
            AXIOS.put(`/${this.props.entityName}s`, newData)
                .then(() => this.showMessage(`${this.props.entityName} was updated`))
                .catch((reason) => this.showMessage(`Error updating ${this.props.entityName}: ${reason}`))
                .finally(() => this.refreshEntities())

            resolve()
        })

    deleteEntity = oldData =>
        new Promise((resolve, reject) => {
            AXIOS.delete(`/${this.props.entityName}s/${oldData.id}`)
                .then(() => this.showMessage(`${this.props.entityName} was deleted`))
                .catch((reason) => this.showMessage(`Error deleting ${this.props.entityName}: ${reason}`))
                .finally(() => this.refreshEntities());

            resolve()
        })


    render() {
        return (
            <>
            <MaterialTable
                title={this.props.tableName}
                columns={this.props.columns}
                data={this.state.data}
                editable={{
                    onRowAdd: this.addEntity,
                    onRowUpdate: this.updateEntity,
                    onRowDelete: this.deleteEntity,
                }}
            />
            <div>{this.state.showMessage ? this.state.infoMessage : null}</div>
            </>
        )
    }
}