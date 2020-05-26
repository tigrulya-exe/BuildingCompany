import React from 'react';
import CrudTable from '../CrudTable'
import WorkersFilter from "../../filters/workers/WorkersFilter";


export default class WorkersTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Post', field: 'post', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Surname', field: 'surname'},
        {title: 'Patronymic', field: 'patronymic'},
        {title: 'Experience Years', field: 'experienceYears'},
        {title: 'Brigade Id', field: 'brigadeId'},
    ];

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/workers'
                tableName='Workers'
                filterForm={<WorkersFilter/>}
            />
        )
    }
}