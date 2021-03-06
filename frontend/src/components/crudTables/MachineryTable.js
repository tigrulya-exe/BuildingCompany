import React from 'react';
import CrudTable from './CrudTable'
import MachineryFilter from '../filters/MachineryFilter'


export default class MachineryTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Licence plate number', field: 'licencePlateNumber'},
        {title: 'Type', field: 'type'},
        {title: 'Building object id', field: 'buildingObjectId', type: 'numeric'},
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/machinery'
                tableName='Machinery'
                filterForm={<MachineryFilter/>}
            />
        )
    }
}