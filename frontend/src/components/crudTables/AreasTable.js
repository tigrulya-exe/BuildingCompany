import React from 'react';
import CrudTable from './CrudTable'
import AreasFilter from '../filters/AreasFilter'

export default class AreasTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Manager Id', field: 'managerId', type: 'numeric'},
        {title: 'Management Id', field: 'managementId', type: 'numeric'},
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                entityName='area'
                tableName='Areas'
                filterForm={<AreasFilter/>}
            />
        )
    }
}