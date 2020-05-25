import React from 'react';
import CrudTable from './CrudTable'
import BuildingObjectsFilter from '../filters/BuildingObjectsFilter'

export default class BuildingObjectsTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Area Id', field: 'areaId', type: 'numeric'},
        {title: 'Customer Id', field: 'customerId', type: 'numeric'}
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/building-objects'
                tableName='Building objects'
                filterForm={<BuildingObjectsFilter/>}
            />
        )
    }
}