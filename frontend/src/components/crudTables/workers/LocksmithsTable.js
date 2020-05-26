import React from 'react';
import CrudTable from '../CrudTable'
import LocksmithsFilter from "../../filters/workers/LocksmithsFilter";


export default class LocksmithsTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Surname', field: 'surname'},
        {title: 'Patronymic', field: 'patronymic'},
        {title: 'Experience Years', type: 'numeric', field: 'experienceYears'},
        {title: 'Brigade Id', type: 'numeric', field: 'brigadeId'},
        {title: 'Category', type: 'numeric', field: 'category'},
        {title: 'Higher education', type: 'boolean', field: 'higherEducation'},

    ];

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/locksmiths'
                tableName='Locksmiths'
                filterForm={<LocksmithsFilter/>}
            />
        )
    }
}