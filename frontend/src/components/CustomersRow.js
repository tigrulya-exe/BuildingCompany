import React from 'react';


export default class CustomersRow extends React.Component{
    render(){
        return (
            <>
                <td>{this.props.id}</td>
                <td>{this.props.name}</td>
            </>
    )}
}