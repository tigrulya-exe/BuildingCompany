import React from 'react';
import CrudTable from './CrudTable'

export default class BuildingObjectsTable extends React.Component {
    columns = [
        { title: 'Id', field: 'id', type: 'numeric', editable: 'never' },
        { title: 'Name', field: 'name' },
        { title: 'Area Id', field: 'areaId', type: 'numeric'},
        { title: 'Customer Id', field: 'customerId', type: 'numeric'}
    ]

    render() {
        return (
            <CrudTable 
                columns={this.columns}
                entityName='building-object'
                tableName='Building objects' 
            />
        )
    }
}