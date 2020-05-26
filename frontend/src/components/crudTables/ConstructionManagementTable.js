import React from 'react';
import CrudTable from './CrudTable'
import ConstructionManagementsFilter from '../filters/ConstructionManagementsFilter'
import ManagerInfo from '../extendedInfos/ManagerInfo'

export default class ConstructionManagementTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Manager Id', field: 'managerId', type: 'numeric'}
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/construction-managements'
                tableName='Construction Managements'
                filterForm={<ConstructionManagementsFilter/>}
                detailPanel={(rowData) => <ManagerInfo id={rowData.managerId}/>}
                onRowClick={(event, rowData, togglePanel) => togglePanel()}
            />
        )
    }
}