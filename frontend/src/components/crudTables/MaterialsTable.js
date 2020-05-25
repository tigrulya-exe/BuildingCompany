import React from 'react';
import CrudTable from './CrudTable'
import MaterialsFilter from '../filters/MaterialsFilter'

export default class MaterialsTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/materials'
                tableName='Materials'
                filterForm={<MaterialsFilter/>}
            />
        )
    }
}