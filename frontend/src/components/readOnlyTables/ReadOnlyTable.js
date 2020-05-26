import React from 'react';
import MaterialTable from 'material-table';
import {AXIOS} from '../../util/AxiosConfig'

export default class ReadOnlyTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            modalMessage: '',
            modalTitle: '',
            showModal: false,
            params: this.props.params,
            formState: null,
        };

        this.firstFilter = false;
        this.tableRef = React.createRef();
    }

    onModalClose = () => {
        this.setState({
            showModal : false
        })
    };

    onFilterSubmit = (state) => {
        this.setState({
            formState: state,
        });

        this.firstFilter = state !== null;
        this.tableRef.current.onQueryChange()
    };

    showModal = (text, title)=> {
        this.setState({
            modalTitle: title,
            modalMessage: text,
            showModal: true
        });
    }

    showError = (text) => this.showModal(text, 'Error!');

    showSuccess = (text) => this.showModal(text, 'Success!');

    getErrorFromReason = (reason) => reason.response?.data?.error;

    resolveResults = (resolve, response) => {
        resolve({
            data: response.data.content,
            page: response.data.pageNumber,
            totalCount: response.data.totalElements,
        })
    };


    getAllEntities = (query) => {
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

    getFilteredEntities = (query) => {
        const params = Object.assign(this.getQueryParams(query), this.state.formState);
        if(this.firstFilter){
            params.page = 0;
            this.firstFilter = false;
        }
        return AXIOS.get(`${this.props.url}/filter`, {params});
    };

    render() {
        return (
            <MaterialTable
                tableRef={this.tableRef}
                title={this.props.tableName}
                columns={this.props.columns}
                data={query =>
                    new Promise((resolve, reject) => {
                        const requestPromise = this.state.formState
                            ? this.getFilteredEntities(query)
                            : this.getAllEntities(query);

                        requestPromise
                            .then(response => this.resolveResults(resolve, response))
                            .catch((reason) => {
                                this.showError(`Error refreshing entities: ${this.getErrorFromReason(reason)}`);
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
                    filtering: this.props.filterForm,
                    pageSizeOptions: [],
                    selection: this.props.onSelectSubmit,
                    selectionProps: this.props.selectionProps
                }}
                onSelectionChange={this.props.onSelectSubmit && ((rows) => {
                    this.props.onSelectSubmit(rows)
                })}
                components={this.props.filterForm && {
                    FilterRow: (props) => React.cloneElement(this.props.filterForm, {
                        onSubmit: this.onFilterSubmit, outerState: this.state.formState
                    })
                }}
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