import React from 'react';
import CrudTable from './CrudTable'

export default class TechnicalSpecialistsTable extends React.Component {
    columns = [
        {title: 'Id', field: 'id', type: 'numeric', editable: 'never'},
        {title: 'Name', field: 'name'},
        {title: 'Surname', field: 'surname'},
        {title: 'Patronymic', field: 'patronymic'},
        {title: 'Educational Institution', field: 'educationalInstitution'},
        {title: 'Expirience Years', field: 'expirienceYears'},
        {title: 'Area Id', field: 'areaId'},
        {title: 'Post', field: 'post'},
        {title: 'Knowledge of English', field: 'knowledgeOfEnglish'},
        {title: 'Category', field: 'category'},
    ]

    render() {
        return (
            <CrudTable
                columns={this.columns}
                entityName='technical-specialists'
                tableName='Technical Specialists'
            />
        )
    }
}