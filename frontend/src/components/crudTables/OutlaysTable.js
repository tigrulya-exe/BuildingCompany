import React from 'react';
import CrudTable from './CrudTable'

export default class OutlaysTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Material Count', field: 'materialCount', type: 'numeric'},
        {title: 'Material Id', field: 'materialId', type: 'numeric'},
        {title: 'Shedule Row Id', field: 'scheduleRowId', type: 'numeric'},
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