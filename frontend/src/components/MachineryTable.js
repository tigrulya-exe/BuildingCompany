import React from 'react';
import CrudTable from './CrudTable'

export default class MachineryTable extends React.Component {
    columns = [
        { title: 'Id', field: 'id', type: 'numeric', editable: 'never' },
        { title: 'Licence plate number', field: 'licencePlateNumber' },
        { title: 'Type', field: 'type'}
    ]

    render() {
        return (
            <CrudTable 
                columns={this.columns}
                entityName='machinery'
                tableName='Machinery' 
            />
        )
    }
}