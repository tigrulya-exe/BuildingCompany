import React from 'react';
import MaterialsFilter from '../filters/MaterialsFilter'
import SelectTable from "./SelectTable";

export default class MaterialsSelectTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
    ]

    render() {
        return (
            <SelectTable
                columns={this.columns}
                url={this.props.url || '/materials'}
                tableName='Materials'
                filterForm={<MaterialsFilter/>}
            />
        )
    }
}