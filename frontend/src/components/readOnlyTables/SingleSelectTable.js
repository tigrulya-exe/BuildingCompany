import React from 'react';
import SelectTable from "./SelectTable";

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
            <SelectTable
                {...this.props}
                selectionProps={rowData => ({
                    disabled: rowData.id !== this.state.checkedId && this.state.isChecked,
                })}
                onSelectSubmit={this.props.onSelectSubmit && ((rows) => {
                    this.setState({
                        isChecked: rows.length,
                        checkedId: (rows.length && rows[0])
                    });
                    this.props.onSelectSubmit((rows.length && rows[0]) || null)
                })}
            />
        )
    }
}