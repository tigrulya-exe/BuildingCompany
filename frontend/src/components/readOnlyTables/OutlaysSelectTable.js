import React from 'react';
import OutlaysFilter from '../filters/OutlaysFilter'
import SelectTable from "./SelectTable";
import ExceedanceInfo from "../extendedInfos/ExceedanceInfo";

export default class OutlaysSelectTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Material Count', field: 'materialCount', type: 'numeric'},
        {title: 'Material Id', field: 'materialId', type: 'numeric'},
        {title: 'Building Object Id', field: 'buildingObjectId', type: 'numeric'},
    ]

    render() {
        return (
            <SelectTable
                {...this.props}
                columns={this.columns}
                url={this.props.url || '/outlays'}
                tableName='Outlays'
                filterForm={<OutlaysFilter/>}
                detailPanel={(rowData) => <ExceedanceInfo outlayId={rowData.id}/>}
                onRowClick={(event, rowData, togglePanel) => togglePanel()}
            />
        )
    }
}