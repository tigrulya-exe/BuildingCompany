import React from 'react';
import ReadOnlyTable from "./ReadOnlyTable";


export default class WorkTypesReadOnlyTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
    ];

    render() {
        return (
            <ReadOnlyTable
                {...this.props}
                columns={this.columns}
                url={this.props.url || '/work-types'}
                tableName='Work types'
            />
        )
    }
}