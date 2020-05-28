import React from 'react';
import CrudTable from '../CrudTable'
import BridgesFilter from "../../filters/buildingObjects/BridgesFilter";
import WorkTypesInfo from "../../extendedInfos/WorkTypesInfo";

export default class BridgesTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Area Id', field: 'areaId', type: 'numeric'},
        {title: 'Customer Id', field: 'customerId', type: 'numeric'},
        {title: 'Width in metres', field: 'widthInMetres', type: 'numeric'},
        {title: 'Type Of Span', field: 'typeOfSpan'},
        {title: 'Number of traffic lanes', field: 'numberOfTrafficLanes', type: 'numeric'},
    ];

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/bridges'
                tableName='Bridges'
                filterForm={<BridgesFilter/>}
                detailPanel={(rowData) => <WorkTypesInfo buildingObjectId={rowData.id}/>}
                onRowClick={(event, rowData, togglePanel) => togglePanel()}
            />
        )
    }
}