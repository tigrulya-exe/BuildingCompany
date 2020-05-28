import React from 'react';
import TechnicalSpecialistsFilter from '../../filters/technicalStuff/TechnicalSpecialistsFilter'
import SelectTable from "../../readOnlyTables/SelectTable";


export default class TechnicalSpecialistsTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Surname', field: 'surname'},
        {title: 'Patronymic', field: 'patronymic'},
        {title: 'Educational Institution', field: 'educationalInstitution'},
        {title: 'Experience Years', field: 'experienceYears'},
        {title: 'Area Id', field: 'areaId'},
        {title: 'Post', field: 'post'},
    ];

    render() {
        return (
            <SelectTable
                columns={this.columns}
                url='/technical-specialists'
                tableName='Technical Specialists'
                filterForm={<TechnicalSpecialistsFilter/>}
            />
        )
    }
}