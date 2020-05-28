import React from 'react';
import WorkersFilter from "../../filters/workers/WorkersFilter";
import SelectTable from "../../readOnlyTables/SelectTable";


export default class WorkersTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Post', field: 'post', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Surname', field: 'surname'},
        {title: 'Patronymic', field: 'patronymic'},
        {title: 'Experience Years', field: 'experienceYears'},
        {title: 'Brigade Id', field: 'brigadeId', sortField: 'brigade'},
    ];

    render() {
        return (
            <SelectTable
                columns={this.columns}
                tableName='Workers'
                filterForm={<WorkersFilter/>}
                params={this.props.params}
                url={this.props.url || '/workers'}
                onSelectSubmit={this.props.onSelectSubmit}
            />
        )
    }
}