import React from 'react';
import CrudTable from './CrudTable'
import AreasFilter from '../filters/AreasFilter'
import ManagerInfo from '../extendedInfos/ManagerInfo'


export default class AreasTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Manager Id', field: 'managerId', type: 'numeric'},
        {title: 'Management Id', field: 'managementId', type: 'numeric'},
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/areas'
                tableName='Areas'
                filterForm={<AreasFilter/>}
                detailPanel={(rowData) => <ManagerInfo id={rowData.managerId}/>}
                onRowClick={(event, rowData, togglePanel) => togglePanel()}
            />
        )
    }
}