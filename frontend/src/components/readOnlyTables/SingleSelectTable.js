import React from 'react';
import ReadOnlyTable from "./ReadOnlyTable";

export default class SingleSelectTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isChecked: false,
            checkedId: null
        };

        this.tableRef = React.createRef();
    }

    render() {
        return (
            <ReadOnlyTable
                tableName={this.props.tableName}
                columns={this.props.columns}
                detailPanel={this.props.detailPanel}
                onRowClick={this.props.onRowClick}
                params={this.props.params}
                url={this.props.url}
                selectionProps={rowData => ({
                    disabled: rowData.id !== this.state.checkedId && this.state.isChecked,
                })}
                onSelectSubmit={this.props.onSelectSubmit && ((rows) => {
                    this.setState({
                        isChecked: rows.length,
                        checkedId: (rows.length && rows[0].id)
                    });
                    this.props.onSelectSubmit((rows.length && rows[0].id) || null)
                })}
            />
        )
    }
}