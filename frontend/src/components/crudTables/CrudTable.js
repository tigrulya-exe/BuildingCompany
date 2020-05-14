import React from 'react';
import MaterialTable from 'material-table';
import {AXIOS} from '../http-common'
import {MTableFilterRow} from 'material-table'

export default class CrudTable extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            // data: [],
            infoMessage: '',
            showMessage: false
        }
    }

    showMessage(text) {
        this.setState({
            infoMessage: text,
            showMessage: true
        })

        setInterval(() => this.setState({showMessage: false}), 6000)
    }

    addEntity = newData =>
        new Promise(resolve => {
            AXIOS.post(`/${this.props.entityName}s`, newData)
                .then(() => this.showMessage(`${this.props.entityName} was added`))
                .catch((reason) => this.showMessage(`Error adding ${this.props.entityName}: ${reason}`))
            resolve()
        })

    updateEntity = (newData, oldData) =>
        new Promise((resolve, reject) => {
            AXIOS.put(`/${this.props.entityName}s`, newData)
                .then(() => this.showMessage(`${this.props.entityName} was updated`))
                .catch((reason) => this.showMessage(`Error updating ${this.props.entityName}: ${reason}`))

            resolve()
        })

    deleteEntity = oldData =>
        new Promise((resolve, reject) => {
            AXIOS.delete(`/${this.props.entityName}s/${oldData.id}`)
                .then(() => this.showMessage(`${this.props.entityName} was deleted`))
                .catch((reason) => this.showMessage(`Error deleting ${this.props.entityName}: ${reason}`))

            resolve()
        })


    render() {
        return (
            <>
                <MaterialTable
                    title={this.props.tableName}
                    columns={this.props.columns}
                    data={query =>
                        new Promise((resolve, reject) => {
                            AXIOS.get(
                                `/${this.props.entityName}s`,
                                {
                                    params: {
                                        page: query.page,
                                        size: query.pageSize
                                    }
                                })
                                .then(response => {
                                    resolve({
                                        data: response.data.content,
                                        page: response.data.pageNumber,
                                        totalCount: response.data.totalElements,
                                    })
                                })
                                .catch((reason) => {
                                    this.showMessage(`Error refreshing ${this.props.entityName}s: ${reason}`)
                                    reject()
                                })
                        })
                    }
                    options={{
                        actionsColumnIndex: -1,
                        filtering: true,
                        search: false
                    }}
                    editable={{
                        onRowAdd: this.addEntity,
                        onRowUpdate: this.updateEntity,
                        onRowDelete: this.deleteEntity,
                    }}
                    components={{
                        FilterRow: this.props.filterForm || MTableFilterRow
                    }}
                />
                <div>{this.state.showMessage ? this.state.infoMessage : null}</div>
            </>
        )
    }
}