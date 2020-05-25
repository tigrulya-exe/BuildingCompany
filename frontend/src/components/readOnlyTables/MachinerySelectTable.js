import React from 'react';
import SingleSelectTable from "./SingleSelectTable";

export default class MachinerySelectTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Licence plate number', field: 'licencePlateNumber'},
        {title: 'Type', field: 'type'}
    ]

    render() {
        return (
            <SingleSelectTable
                columns={this.columns}
                url={this.props.url || '/machinery'}
                tableName='Machinery'
                params={this.props.params}
                onSelectSubmit={this.props.onSelectSubmit}
            />
        )
    }
}