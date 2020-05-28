import React from 'react';
import WorkTypesReadOnlyTable from "./WorkTypesReadOnlyTable";


export default class WorkTypesSelectTable extends React.Component {
    render() {
        return (
            <WorkTypesReadOnlyTable
                params={this.props.params}
                url={this.props.url || '/work-types'}
                tableName='Work types'
                onSelectSubmit={this.props.onSelectSubmit}
            />
        )
    }
}