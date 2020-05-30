import React from 'react';
import WorkScheduleFilter from '../filters/WorkScheduleFilter'
import SelectTable from "./SelectTable";

export default class WorkScheduleTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Start date', field: 'startDate', type: 'datetime'},
        {title: 'End date', field: 'endDate', type: 'datetime'},
        {title: 'Building Object Id', field: 'buildingObjectId', type: 'numeric'},
        {title: 'Work type ', field: 'workType'},
        {title: 'Brigade Id', field: 'brigadeId', type: 'numeric'},
    ];

    render() {
        return (
            <SelectTable
                {...this.props}
                columns={this.columns}
                url={this.props.url || '    /work-schedules'}
                tableName='WorkSchedule'
                filterForm={<WorkScheduleFilter/>}
            />
        )
    }
}