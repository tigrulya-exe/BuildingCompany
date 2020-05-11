import React from 'react';
import CrudTable from './CrudTable'

export default class BrigadesTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Manager Id', field: 'managerId', type: 'numeric'},
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                entityName='brigade'
                tableName='Brigades'
            />
        )
    }
}