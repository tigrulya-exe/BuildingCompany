import React from 'react';
import CrudTable from './CrudTable'
import WorkScheduleFilter from '../filters/WorkScheduleFilter'

export default class WorkScheduleTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Start date', field: 'startDate', type: 'datetime'},
        {title: 'End date', field: 'endDate', type: 'datetime'},
        {title: 'Building Object Id', field: 'buildingObjectId', type: 'numeric'},
        {title: 'Work type ', field: 'workType'},
        {title: 'Brigade Id', field: 'brigadeId', type: 'numeric'},
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                entityName='work-schedule'
                tableName='WorkSchedule'
                filterForm={<WorkScheduleFilter/>}
            />
        )
    }
}