import React from 'react';
import CrudTable from './CrudTable'
import OutlaysFilter from '../filters/OutlaysFilter'

export default class OutlaysTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Material Count', field: 'materialCount', type: 'numeric'},
        {title: 'Material Id', field: 'materialId', type: 'numeric'},
        {title: 'Building Object Id', field: 'buildingObjectId', type: 'numeric'},
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/outlays'
                tableName='Outlays'
                filterForm={<OutlaysFilter/>}
            />
        )
    }
}