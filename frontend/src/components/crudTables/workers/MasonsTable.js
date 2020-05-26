import React from 'react';
import CrudTable from '../CrudTable'
import MasonsFilter from "../../filters/workers/MasonsFilter";


export default class MasonsTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Surname', field: 'surname'},
        {title: 'Patronymic', field: 'patronymic'},
        {title: 'Experience Years', type: 'numeric', field: 'experienceYears'},
        {title: 'Brigade Id', type: 'numeric', field: 'brigadeId'},
        {title: 'Bricks per hour', type: 'numeric', field: 'bricksPerHour'},
    ];

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/masons'
                tableName='Masons'
                filterForm={<MasonsFilter/>}
            />
        )
    }
}