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
        this.ToChangeInfo=this.ToChangeInfo.bind(this)
    }
    componentDidMount (){
        let uid=localStorage.getItem("uid");
        Axios.post("/userInfoTest",{
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

    ToChangeInfo(){
        alert("OK")
    }

    render() {
        return(
            <div>
                <Nav/>
                <UserLeftNav/>
                <PersonalInfo toChange={this.state.toChange}
                              user={this.state.user} ToChangeInfo={this.ToChangeInfo}/>
            </div>
            )
    }
}

function PersonalInfo(props){
    if(!props.toChange){
        return(
            <div className="UserRight">
                <h1 className="UserTitle">个人资料</h1>
                <hr/>
                <div className="userInfo">
                    昵称:<input value={props.user.uname} placeholder="你的昵称"/><br/><br/>
                    性别:<span>{props.user.gender}</span><br/><br/>
                    邮箱:<span>{props.user.email}</span><br/><br/>
                    生日:<span>{dateTransferSimple(props.user.birth)}</span><br/>
                </div>
                <button id="saveProfile" onClick={props.ToChangeInfo}>保存信息</button>
            </div>
        )
    }else{//修改信息
        return (
            <div>

            </div>
        )
    }
}

export default connect()(Profile)