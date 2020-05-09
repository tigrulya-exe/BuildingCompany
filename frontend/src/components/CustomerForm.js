import React from 'react';

export default class CustomerForm extends React.Component{
    constructor(props){
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.state = {
                name: ''
        }
    }

    handleChange(event){
        this.setState({
            name: event.target.value
        })
    }

    handleSubmit(event) {
        if(this.state.name === ''){
            alert("Wrong name!")
            event.preventDefault();
            return
        }

        this.props.addCustomer(this.state.name)  
        this.setState({
            name: ''
        })
        event.preventDefault();
    }

    render(){
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    Имя:
                        <input type="text" name="name" value={ this.state.name } onChange={ this.handleChange } />
                </label>
                <input type="submit" value="Отправить" />
            </form>
        )
    }
}