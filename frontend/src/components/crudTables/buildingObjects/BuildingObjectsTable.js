import React from 'react';
import BuildingObjectsFilter from '../../filters/buildingObjects/BuildingObjectsFilter'
import SelectTable from "../../readOnlyTables/SelectTable";
import WorkTypesInfo from "../../extendedInfos/WorkTypesInfo";

export default class BuildingObjectsTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Area Id', field: 'areaId', type: 'numeric'},
        {title: 'Customer Id', field: 'customerId', type: 'numeric'}
    ]

    render() {
        return (
            <SelectTable
                columns={this.columns}
                url='/building-objects'
                tableName='Building objects'
                detailPanel={(rowData) => <WorkTypesInfo buildingObjectId={rowData.id}/>}
                onRowClick={(event, rowData, togglePanel) => togglePanel()}
                filterForm={<BuildingObjectsFilter/>}
            />
        )
    }
}