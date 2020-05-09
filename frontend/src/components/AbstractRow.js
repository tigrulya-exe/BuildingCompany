import React from 'react';


export default class AbstractRow extends React.Component{
    constructor(props){
        super(props);
        this.onDelete = this.onDelete.bind(this)
    }

    onDelete(e){
        this.props.onDeleteCallback(e.target.id)
    }

    render(){
        return (
            <tr>
                <>{this.props.children}</>
                <td><button id={this.props.id} onClick={this.onDelete}>Delete</button></td>
            </tr>
    )}
}