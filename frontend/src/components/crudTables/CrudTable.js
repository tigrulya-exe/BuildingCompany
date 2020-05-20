import React from 'react';
import MaterialTable from 'material-table';
import {AXIOS} from '../http-common'

export default class CrudTable extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            infoMessage: '',
            showMessage: false,
            tableRef: React.createRef(),
            formState: null,
        }
    }

    onFilterSubmit = (state) => {
        this.setState({formState: state})
        this.state.tableRef.current.onQueryChange()
    }

    showMessage(text) {
        console.log(text)
        this.setState({
            infoMessage: text,
            showMessage: true
        })
        console.log("Interval: " + text)
        setTimeout(() => this.setState({showMessage: false}), 6000)
    }

    addEntity = newData =>
        new Promise(resolve => {
            AXIOS.post(`/${this.props.entityName}s`, newData)
                .then(() => {
                    this.showMessage(`${this.props.entityName} was added`)
                    this.state.tableRef.current.onQueryChange();
                })
                .catch((reason) => this.showMessage(`Error adding ${this.props.entityName}: ${reason}`))
            resolve()
        })

    updateEntity = (newData, oldData) =>
        new Promise((resolve, reject) => {
            AXIOS.put(`/${this.props.entityName}s`, newData)
                .then(() => {
                    this.showMessage(`${this.props.entityName} was updated`)
                    this.state.tableRef.current.onQueryChange();
                })
                .catch((reason) => this.showMessage(`Error updating ${this.props.entityName}: ${reason}`))

            resolve()
        })

    deleteEntity = oldData =>
        new Promise((resolve, reject) => {
            AXIOS.delete(`/${this.props.entityName}s/${oldData.id}`)
                .then(() => {
                    this.showMessage(`${this.props.entityName} was deleted`)
                    this.state.tableRef.current.onQueryChange();
                })
                .catch((reason) => this.showMessage(`Error deleting ${this.props.entityName}: ${reason}`))

            resolve()
        });

    resolveResults = (resolve, response) => {
        resolve({
            data: response.data.content,
            page: response.data.pageNumber,
            totalCount: response.data.totalElements,
        })
    }

    getAllEntities = (query) => {
        console.log("page " + query.page)
        console.log("pageSize " + query.pageSize)

        return AXIOS.get(
            `/${this.props.entityName}s`,
            {
                params: this.getQueryParams(query)
            });
    }

    getQueryParams = (query) => ({
        page: query.page,
        pageSize: query.pageSize,
        orderBy: query.orderBy && query.orderBy.field,
        order: query.orderDirection && (query.orderDirection === 'asc' ? 'ASCENDING' : 'DESCENDING')
    })

    getFilteredEntities = (query) => {
        const params = Object.assign(this.getQueryParams(query), this.state.formState)
        return AXIOS.get(
            `/${this.props.entityName}s/filter`,
            {params});
    }

    render() {
        return (
            <>
                <MaterialTable
                    tableRef={this.state.tableRef}
                    title={this.props.tableName}
                    columns={this.props.columns}
                    data={query =>
                        new Promise((resolve, reject) => {
                            const requestPromise = this.state.formState
                                ? this.getFilteredEntities(query) : this.getAllEntities(query)
                            requestPromise
                                .then(response => this.resolveResults(resolve, response))
                                .catch((reason) => {
                                    this.showMessage(`Error refreshing ${this.props.entityName}s: ${reason}`)
                                    resolve({
                                        data: [],
                                        page: 0,
                                        totalCount: 0
                                    })
                                })
                        })
                    }
                    options={{
                        actionsColumnIndex: -1,
                        filtering: true,
                        search: false,
                        pageSize: 8,
                        pageSizeOptions: []
                    }}
                    editable={{
                        onRowAdd: this.addEntity,
                        onRowUpdate: this.updateEntity,
                        onRowDelete: this.deleteEntity,
                    }}
                    components={{
                        FilterRow: (props) => React.cloneElement(this.props.filterForm, {
                            onSubmit: this.onFilterSubmit, outerState: this.state.formState
                        })
                    }}
                    detailPanel={this.props.detailPanel}
                    onRowClick={this.props.onRowClick}
                />
                <div>{this.state.showMessage ? this.state.infoMessage : null}</div>
            </>
        )
    }
}