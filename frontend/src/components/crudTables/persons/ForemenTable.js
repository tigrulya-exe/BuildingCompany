import React from 'react';
import CrudTable from '../CrudTable'
import TechnicalSpecialistsFilter from '../../filters/TechnicalSpecialistsFilter'


export default class ForemenTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Surname', field: 'surname'},
        {title: 'Patronymic', field: 'patronymic'},
        {title: 'Educational Institution', field: 'educationalInstitution'},
        {title: 'Experience Years', field: 'experienceYears'},
        {title: 'Area Id', field: 'areaId'},
        {title: 'Post', field: 'post'},
        {title: 'Knowledge of English', field: 'knowledgeOfEnglish', type: 'boolean'},
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                url='/foremen'
                tableName='Foremen'
                filterForm={<TechnicalSpecialistsFilter/>}
            />
        )
    }
}