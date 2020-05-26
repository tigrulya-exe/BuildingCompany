import React from 'react';
import SingleSelectTable from "./SingleSelectTable";

export default class MachinerySelectTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Licence plate number', field: 'licencePlateNumber'},
        {title: 'Type', field: 'type'},
        {title: 'Building object id', field: 'buildingObjectId', type: 'numeric'},
    ]

    render() {
        return (
            <SingleSelectTable
                {...this.props}
                columns={this.columns}
                url={this.props.url || '/machinery'}
                tableName='Machinery'
            />
        )
    }
}