import React from 'react';
import MaterialTable from 'material-table';
import { AXIOS } from './http-common'

export default class CustomerTable extends React.Component {
    constructor(props) {
        super(props)
        this.refreshCustomers()
        this.state = {
            columns: [
                { title: 'Id', field: 'id', type: 'numeric', editable: 'never' },
                { title: 'Name', field: 'name' },
            ],
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

    refreshCustomers = () => {
        AXIOS.get('/customers')
            .then(response => {
                this.setState({ data: response.data.content })
            })
            .catch((reason) => this.showMessage(reason))
    }

    addCustomer = newData =>
        new Promise(resolve => {
            AXIOS.post('/customers', newData)
                .then(() => this.showMessage('Customer was added'))
                // тут ексепшон кидает мол объект ето не дитя реакта...
                .catch((reason) => this.showMessage(`Error adding customer: ${reason}`))
                .finally(() => this.refreshCustomers())
            resolve()
        })

    updateCustomer = (newData, oldData) =>
        new Promise((resolve, reject) => {
            AXIOS.put('/customers', newData)
                .then(() => this.showMessage('Customer was updated'))
                .catch((reason) => this.showMessage(`Error updating customer: ${reason}`))
                .finally(() => this.refreshCustomers())

            resolve()
        })

    deleteCustomer = oldData =>
        new Promise((resolve, reject) => {
            AXIOS.delete(`/customers/${oldData.id}`)
                .then(() => this.showMessage('Customer was deleted'))
                .catch((reason) => this.showMessage(`Error deleting customer: ${reason}`))
                .finally(() => this.refreshCustomers());

            resolve()
        })


    render() {
        return (
            <>
            <MaterialTable
                title="Customers"
                columns={this.state.columns}
                data={this.state.data}
                editable={{
                    editable: name => name !== 'Id',
                    onRowAdd: this.addCustomer,
                    onRowUpdate: this.updateCustomer,
                    onRowDelete: this.deleteCustomer,
                }}
            />
            <div>{this.state.showMessage ? this.state.infoMessage : null}</div>
            </>
        )
    }
}