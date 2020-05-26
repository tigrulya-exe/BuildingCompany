import React from 'react';
import ManagerInfo from '../extendedInfos/ManagerInfo'
import ReadOnlyTable from "./ReadOnlyTable";

export default class ConstructionManagementTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Manager Id', field: 'managerId', type: 'numeric'}
    ]

    render() {
        return (
            <ReadOnlyTable
                columns={this.columns}
                url='/construction-managements'
                tableName='Construction Managements'
                detailPanel={(rowData) => <ManagerInfo id={rowData.managerId}/>}
                onRowClick={(event, rowData, togglePanel) => togglePanel()}
                onSelectSubmit={this.props.onSelectSubmit}
            />
        )
    }
}