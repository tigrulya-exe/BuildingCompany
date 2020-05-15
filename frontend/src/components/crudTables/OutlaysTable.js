import React from 'react';
import CrudTable from './CrudTable'

export default class OutlaysTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Material Count', field: 'materialCount', type: 'numeric'},
        {title: 'Material Id', field: 'materialId', type: 'numeric'},
        {title: 'Building Object Id', field: 'buildingObjectId', type: 'numeric'},
        {title: 'Work type Id', field: 'workTypeId', type: 'numeric'},
        {title: 'Brigade Id', field: 'brigadeId', type: 'numeric'},
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                entityName='outlay'
                tableName='Outlays'
            />
        )
    }
}