import React from 'react';
import CrudTable from '../crudTables/CrudTable'
import PermissionsInfo from "../extendedInfos/PermissionsInfo";
import RolesFilter from "../filters/RolesFilter";

export default class RolesTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Role name', field: 'role'},
    ];

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/roles'
                tableName='Roles'
                detailPanel={(rowData) => <PermissionsInfo roleId={rowData.id}/>}
                onRowClick={(event, rowData, togglePanel) => togglePanel()}
            />
        )
    }
}