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
        AXIOS.get('/customers')
            .then(response => {
                this.setState({ data: response.data.content })
            })
            .catch((reason) => this.showMessage(`Error refreshing customers: ${reason}`))
    }

    addEntity = newData =>
        new Promise(resolve => {
            AXIOS.post(`/${this.props.entityName}s`, newData)
                .then(() => this.showMessage('Customer was added'))
                .catch((reason) => this.showMessage(`Error adding customer: ${reason}`))
                .finally(() => this.refreshEntities())
            resolve()
        })

    updateEntity = (newData, oldData) =>
        new Promise((resolve, reject) => {
            AXIOS.put(`/${this.props.entityName}s`, newData)
                .then(() => this.showMessage('Customer was updated'))
                .catch((reason) => this.showMessage(`Error updating customer: ${reason}`))
                .finally(() => this.refreshEntities())

            resolve()
        })

    deleteEntity = oldData =>
        new Promise((resolve, reject) => {
            AXIOS.delete(`/${this.props.entityName}s/${oldData.id}`)
                .then(() => this.showMessage('Customer was deleted'))
                .catch((reason) => this.showMessage(`Error deleting customer: ${reason}`))
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
                    editable: this.props.editableRows,
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