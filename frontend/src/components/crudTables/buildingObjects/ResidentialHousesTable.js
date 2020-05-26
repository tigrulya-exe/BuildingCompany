import React from 'react';
import CrudTable from '../CrudTable'
import ResidentialHousesFilter from "../../filters/buildingObjects/ResidentialHousesFilter";

export default class ResidentialHousesTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Area Id', field: 'areaId', type: 'numeric'},
        {title: 'Customer Id', field: 'customerId', type: 'numeric'},
        {title: 'Flat count', field: 'flatCount', type: 'numeric'},
        {title: 'Floor Count', field: 'floorCount', type: 'numeric'},
    ];

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/residential-houses'
                tableName='Residential houses'
                filterForm={<ResidentialHousesFilter/>}
            />
        )
    }
}