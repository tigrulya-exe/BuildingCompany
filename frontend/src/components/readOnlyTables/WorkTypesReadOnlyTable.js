import React from 'react';
import SelectTable from "./SelectTable";


export default class WorkTypesReadOnlyTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
    ];

    render() {
        return (
            <SelectTable
                {...this.props}
                columns={this.columns}
                url={this.props.url || '/work-types'}
                tableName='Work types'
                onSelectSubmit={this.props.onSelectSubmit}
            />
        )
    }
}