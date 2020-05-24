import React from 'react';
import MaterialTable from 'material-table';
import {AXIOS} from '../../util/AxiosConfig'

export default class SelectTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    resolveResults = (resolve, response) => {
        resolve({
            data: response.data.content,
            page: response.data.pageNumber,
            totalCount: response.data.totalElements,
        })
    };

    getAllEntities = (query) => {
        console.log("page " + query.page);
        console.log("pageSize " + query.pageSize);

        return AXIOS.get(
            `/${this.props.entityName}s`,
            {
                params: this.getQueryParams(query)
            });
    };

    getQueryParams = (query) => ({
        page: query.page,
        pageSize: query.pageSize,
        orderBy: query.orderBy && query.orderBy.field,
        order: query.orderDirection && (query.orderDirection === 'asc' ? 'ASCENDING' : 'DESCENDING')
    });

    render() {
        return (
            <MaterialTable
                title={this.props.tableName}
                columns={this.props.columns}
                data={query =>
                    new Promise((resolve, reject) => {
                        const requestPromise = this.getAllEntities(query);
                        requestPromise
                            .then(response => this.resolveResults(resolve, response))
                            .catch((reason) => {
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
                    search: false,
                    pageSize: 8,
                    pageSizeOptions: [],
                    selection: true
                }}
                onSelectionChange={(rows) => {
                    if (rows.length === 0) {
                        this.props.onSelectSubmit([])
                    }
                }}
                actions={[
                    {
                        tooltip: 'Remove All Selected Users',
                        icon: 'add',
                        // onClick: (evt, data) => alert('You want to delete ' + data.length + ' rows')
                        onClick: (evt, data) => this.props.onSelectSubmit(data)
                    }
                ]}
                detailPanel={this.props.detailPanel}
                onRowClick={this.props.onRowClick}
            />
        )
    }
}