import React from 'react';
import CrudTable from './CrudTable'

export default class ConstructionManagementTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Manager Id', field: 'customerId', type: 'numeric'}
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                entityName='construction-management'
                tableName='Construction Managements'
            />
        )
    }
}