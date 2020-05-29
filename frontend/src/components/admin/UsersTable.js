import React from 'react';
import CrudTable from '../crudTables/CrudTable'
import RolesInfo from "../extendedInfos/RolesInfo";
import UserFilter from "../filters/UserFilter";

export default class UsersTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Nickname', field: 'nickname'},
        {title: 'Email', field: 'email'},
        {title: 'FullName', field: 'fullName'},
    ];

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/users'
                tableName='Users'
                detailPanel={(rowData) => <RolesInfo userId={rowData.id}/>}
                onRowClick={(event, rowData, togglePanel) => togglePanel()}
                filterForm={<UserFilter/>}
            />
        )
    }
}