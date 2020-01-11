import React, { Component } from 'react';
//import {connect} from 'react-redux';
//import {hotBlogData} from '../../redux/index/action'
import Axios from "../../axios/axios";
import {openNotificationWithIcon} from "../notification";

class IndexHome extends Component{
    constructor (props) {
        super(props);
        this.state = {
            userid:"",
            name:""
        }
    }
    componentDidMount (){
        Axios.get("/user").then(({data}) => {
            if(data.code === 200){
                this.setState({
                    userid: data.detail.userid,
                    name: data.detail.name
                });
            }else{
                {openNotificationWithIcon("error","Error",data.description)}
            }
        }).catch( error => {
            {openNotificationWithIcon("error","Error",error.message)}
        })
    }
    render(){
        return(
            <div>
                {this.state.name}
            </div>
        )
    }
}

export default IndexHome;