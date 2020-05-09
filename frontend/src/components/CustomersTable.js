import React from 'react';
import CustomersRow from './CustomersRow'
import AbstractRow from './AbstractRow'
import CustomerForm from './CustomerForm'


export default class CustomersTable extends React.Component{
    constructor(props){
        super(props);
        this.deleteCustomer = this.deleteCustomer.bind(this)
        this.addCustomer = this.addCustomer.bind(this)

        this.state = {
            customers: [
                {id: 1, name: 'Ivan'},
                {id: 12, name: 'Sasha'},
                {id: 777, name: 'Sesh'}
            ]
        }
    }
    deleteCustomer(id){
        this.setState({customers: this.state.customers.filter(
            c => c.id != id
        )})
    }

    addCustomer(name){
        const minId = this.state.customers.length
        const id = minId !== 0 ? Math.max.apply(null, this.state.customers.map(c => c.id)) + 1 : 0
        this.state.customers.push({id: id, name: name})
        this.setState({
            customers: this.state.customers
        })
    }

    render(){
        // alert(this.state.customers);
        const values = this.state.customers.map(
            (customer) => (
                <AbstractRow key= {customer.id} id = {customer.id} onDeleteCallback = {this.deleteCustomer}>
                    <CustomersRow id={customer.id} name={customer.name}/>
                </AbstractRow>
            )
        )

        return (
            <>
                <table>{values}</table>
                <CustomerForm addCustomer = {this.addCustomer}/>
            </>
        )
    }
}