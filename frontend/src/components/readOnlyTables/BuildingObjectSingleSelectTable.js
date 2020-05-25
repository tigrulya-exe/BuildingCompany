import React from 'react';
import SingleSelectTable from "./SingleSelectTable";

export default class BuildingObjectsSingleSelectTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Area Id', field: 'areaId', type: 'numeric'},
        {title: 'Customer Id', field: 'customerId', type: 'numeric'}
    ]

    render() {
        return (
            <SingleSelectTable
                columns={this.columns}
                url={this.props.url || '/building-objects'}
                tableName='Building objects'
                params={this.props.params}
                onSelectSubmit={this.props.onSelectSubmit}
            />
        )
    }
}