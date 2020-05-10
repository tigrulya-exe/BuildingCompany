import React from 'react';
import CrudTable from './CrudTable'

export default class CustomerTable extends React.Component {
    columns = [
        { title: 'Id', field: 'id', type: 'numeric', editable: 'never' },
        { title: 'Name', field: 'name' },
    ]

    render() {
        return (
            <CrudTable 
                columns={this.columns}
                entityName='customer'
                tableName='Customers' 
            />
        )
    }
}