import React, { Component } from 'react';
import Axios from "../../axios/axios";
import {connect} from "react-redux";
import UserLeftNav from "./left";
import {Nav} from "../header/header";
import {dateTransferSimple} from "../../tools/tansfer"


import './profile.css'

class Profile extends Component{
    constructor (props) {
        super(props);
        this.state = {
            user:""
        }
    }
    componentDidMount (){
        let uid=localStorage.getItem("uid");
        Axios.post("/userInfo",{
            uid:uid
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    user: data.detail
                });
            }else{
                alert(data.description)
            }
        }).catch( error => {
            alert(error.message)
        })
    }

    render() {
        return(
            <div>
                <Nav/>
                <UserLeftNav/>
                <div className="UserRight">
                    <h1 className="UserTitle">个人资料</h1>
                    <hr/>
                    <div className="userInfo">
                        昵称:<span>{this.state.user.uname}</span><br/><br/>
                        性别:<span>{this.state.user.gender}</span><br/><br/>
                        邮箱:<span>{this.state.user.email}</span><br/><br/>
                        生日:<span>{dateTransferSimple(this.state.user.birth)}</span><br/>
                    </div>
                    <a href="/person/profile/modify">修改信息</a>
                </div>
            </div>
            )
    }
}

export default connect()(Profile)