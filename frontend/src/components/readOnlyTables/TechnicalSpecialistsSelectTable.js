import React from 'react';
import ReadOnlyTable from "./ReadOnlyTable";


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
        {title: 'Knowledge of English', field: 'knowledgeOfEnglish', type: 'boolean'},
        {title: 'Category', field: 'category'},
    ];

    render() {
        return (
            <ReadOnlyTable
                params={this.props.params}
                columns={this.columns}
                url={this.props.url || '/technical-specialists'}
                tableName='Technical specialists'
                onSelectSubmit={this.props.onSelectSubmit}
            />
        )
    }
}