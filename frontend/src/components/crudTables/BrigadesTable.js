import React from 'react';
import CrudTable from './CrudTable'
import BrigadesFilter from '../filters/BrigadesFilter'

export default class BrigadesTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Manager Id', field: 'managerId', type: 'numeric'},
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/brigades'
                tableName='Brigades'
                filterForm={<BrigadesFilter/>}
            />
        )
    }
}