import React from 'react';
import SelectTable from "./SelectTable";
import ObjectScheduleInfo from "../extendedInfos/ObjectScheduleInfo";

export default class BuildingObjectsSelectTable extends React.Component {
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
                url={this.props.url || '/building-objects'}
                tableName='Building objects'
                params={this.props.params}
                detailPanel={(rowData) => <ObjectScheduleInfo objectId={rowData.id}/>}
                onRowClick={(event, rowData, togglePanel) => togglePanel()}
                onSelectSubmit={this.props.onSelectSubmit}
            />
        )
    }
}