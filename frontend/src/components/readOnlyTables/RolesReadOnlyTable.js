import React from 'react';
import SelectTable from "./SelectTable";

export default class RolesReadOnlyTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Role name', field: 'role'},
    ];

    render() {
        return (
            <SelectTable
                {...this.props}
                columns={this.columns}
                url={this.props.url || '/roles'}
                tableName='Roles'
            />
        )
    }
}