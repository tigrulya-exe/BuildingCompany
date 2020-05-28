import React from 'react';
import MaterialTable from 'material-table';
import {AXIOS} from '../../util/AxiosConfig'

export default class SelectTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            modalMessage: '',
            modalTitle: '',
            showModal: false,
            params: this.props.params,
            formState: null,
            selectedRows: []
        };

        this.props.setRefresh && this.props.setRefresh(this.onRefresh);
        this.firstFilter = false;
        this.tableRef = React.createRef();
    }

    onModalClose = () => {
        this.setState({
            showModal: false
        })
    };

    onFilterSubmit = (state) => {
        this.setState({
            formState: state,
        });

        this.firstFilter = state !== null;
        this.tableRef.current.onQueryChange()
    };

    showModal = (text, title) => {
        this.setState({
            modalTitle: title,
            modalMessage: text,
            showModal: true
        });
    };

    showError = (text) => this.showModal(text, 'Error!');

    showSuccess = (text) => this.showModal(text, 'Success!');

    getErrorFromReason = (reason) => reason.response?.data?.error;

    resolveResults = (resolve, response) => {
        const content = response.data.content;

        const selectedData = content?.map(
            row => this.state?.selectedRows.find(selected => selected === row.id)
                ? {...row, tableData: {checked: true}} : row
        );
        resolve({
            data: selectedData,
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
        orderBy: query.orderBy?.sortField || query.orderBy?.field,
        order: query.orderDirection && (query.orderDirection === 'asc' ? 'ASCENDING' : 'DESCENDING')
    });

    getFilteredEntities = (query) => {
        const params = Object.assign(this.getQueryParams(query), this.state.formState);
        if (this.firstFilter) {
            params.page = 0;
            this.firstFilter = false;
        }
        return AXIOS.get(`${this.props.url}/filter`, {params});
    };

    onRefresh = () => {
        this.props.onRefresh && this.props.onRefresh();
        return this.tableRef.current?.onQueryChange()
    };

    handleSelectionChange = (newRows, clickedRow) => {
        const selectedRows = this.state.selectedRows;
        const isChecking = !!(clickedRow?.tableData);
        const distinct = (value, index, self) => self.indexOf(value) === index;
        let newSelectedRows;

        if(!newRows.length && !clickedRow){
            newSelectedRows = []
        } else if (!isChecking || (isChecking && clickedRow.tableData.checked)) {
            let newRowsIds = [...newRows.map(r => r.id)];
            if (selectedRows.length) {
                newRowsIds = [...newRowsIds, ...selectedRows]
            }
            newSelectedRows = newRowsIds.filter(distinct);
        } else {
            const finalRow = selectedRows.filter(rfc => rfc !== clickedRow.id) || [];
            newSelectedRows = finalRow.filter(distinct);
        }

        this.props.onSelectSubmit(newSelectedRows);
        this.setState({selectedRows: newSelectedRows});
    };

    onDelete = (row) => {
        const promise =  this.props.onDelete(row);
        this.tableRef.current.onQueryChange();
        return promise;
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
                onSelectionChange={this.props.onSelectSubmit && this.handleSelectionChange}
                components={this.props.filterForm && {
                    FilterRow: (props) => React.cloneElement(this.props.filterForm, {
                        onSubmit: this.onFilterSubmit, outerState: this.state.formState
                    })
                }}
                editable={{
                    onRowDelete: this.props.onDelete && this.onDelete
                }}
                actions={[
                    {
                        icon: 'refresh',
                        tooltip: 'Refresh Data',
                        isFreeAction: true,
                        onClick: this.onRefresh,
                    },
                    this.props.action
                ]}
                detailPanel={this.props.detailPanel}
                onRowClick={this.props.onRowClick}
            />
        )
    }
}