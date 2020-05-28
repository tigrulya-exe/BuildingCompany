import React from 'react';
import ManagerInfo from '../extendedInfos/ManagerInfo'
import SelectTable from "./SelectTable";


export default class AreaSelectTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Manager Id', field: 'managerId', type: 'numeric'},
        {title: 'Management Id', field: 'managementId', type: 'numeric'},
    ];

    render() {
        return (
            <SelectTable
                columns={this.columns}
                url='/areas'
                tableName='Areas'
                detailPanel={(rowData) => <ManagerInfo id={rowData.managerId}/>}
                onRowClick={(event, rowData, togglePanel) => togglePanel()}
                onSelectSubmit={this.props.onSelectSubmit}
            />
        )
    }
}