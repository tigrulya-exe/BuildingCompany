import React from 'react';
import MaterialTable from 'material-table';
import {AXIOS} from '../../util/AxiosConfig'

export default class ReadOnlyTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            params: this.props.params
        };

        this.tableRef = React.createRef();
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

        return AXIOS.get(this.props.url,
            {
                params: this.getQueryParams(query)
            });
    };

    getQueryParams = (query) => ({
        ...this.props.params,
        page: query.page,
        pageSize: query.pageSize,
        orderBy: query.orderBy && query.orderBy.field,
        order: query.orderDirection && (query.orderDirection === 'asc' ? 'ASCENDING' : 'DESCENDING')
    });

    render() {
        return (
            <MaterialTable
                tableRef={this.tableRef}
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
                    pageSize: 5,
                    pageSizeOptions: [],
                    selection: this.props.onSelectSubmit
                }}
                onSelectionChange={this.props.onSelectSubmit && ((rows) => {
                    this.props.onSelectSubmit(rows)
                })}
                actions={[
                    {
                        icon: 'refresh',
                        tooltip: 'Refresh Data',
                        isFreeAction: true,
                        onClick: () => this.tableRef.current && this.tableRef.current.onQueryChange(),
                    }
                ]}
                detailPanel={this.props.detailPanel}
                onRowClick={this.props.onRowClick}
            />
        )
    }
}