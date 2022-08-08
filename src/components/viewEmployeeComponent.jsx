import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';


class viewEmployeeComponent extends Component {
    constructor(props){
        super(props)
        this.state={
            id:this.props.match.params.id,
            employees:{}
        }
    }
    componentDidMount(){
        console.log(this.state.id);
        EmployeeService.getEmployeeById(this.state.id).then(res=>{
            this.setState({employees: res.data});
        })
    }
    cancel(){
        this.props.history.push('/employees');
    }
    render() {
        return (
            <div>
                <br></br>
               <div className="card col-md-6 offset-md-3">
                <h3 className='text-center'>View Employee Details</h3>
                <div className="card-body">
                    <div className="row">
                       <label>Employee First Name:</label>
                       <div>{ this.state.employees.firstName }</div>
                    </div>
                    <div className="row">
                       <label>Employee Last Name:</label>
                       <div>{this.state.employees.lastName}</div>
                    </div>
                    <div className="row">
                       <label>Employee Email Id:</label>
                       <div>{this.state.employees.emailId}</div>
                    </div>
                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}>Back</button>
                </div>
               </div>
            </div>
        );
    }
}

export default viewEmployeeComponent;