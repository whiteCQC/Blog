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
            user:"",
            newName:"",
            curSex:"",
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
                    user: data.detail,
                    curSex:data.detail.gender,
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
    ChangeName=(e)=>{
        this.setState({
            newName:e.target.value
        })
    }
    ChangeSex=(e)=>{
        this.setState({
            curSex:e.target.value
        })
        alert("here")
    }

    render() {
        return(
            <div>
                <Nav/>
                <UserLeftNav/>
                <PersonalInfo toChange={this.state.toChange}
                              curSex={this.state.curSex}
                              user={this.state.user} ToChangeInfo={this.ToChangeInfo}
                              nameChange={this.ChangeName} ChangeSex={this.ChangeSex}
                />
            </div>
            )
    }
}

function PersonalInfo(props){
    return(
        <div className="UserRight">
            <h1 className="UserTitle">个人资料</h1>
            <hr/>
            <div className="userInfo">
                昵称:<input defaultValue={props.user.uname} placeholder="你的昵称" onChange={props.nameChange}/><br/><br/>
                性别:<Sex sex={props.curSex} SexChange={props.ChangeSex}/>
                <br/><br/>
                邮箱:<span>{props.user.email}</span><br/><br/>
                生日:<span>{dateTransferSimple(props.user.birth)}</span><br/>
            </div>
            <button id="saveProfile" onClick={props.ToChangeInfo}>保存信息</button>
        </div>
    )

}

function Sex(props){
    return(
        <div className="radio-group">
            <label className="el-radio-button" >
                <input type="radio" name="sex" className="radio-button" value="男" checked={props.sex==='男'} onChange={props.SexChange}/>
                <span className="radio-button-inner">男</span>
            </label>
            <label className="el-radio-button">
                <input type="radio" name="sex" className="radio-button" value="女" checked={props.sex==='女'} onChange={props.SexChange}/>
                <span className="radio-button-inner">女</span>
            </label>
            <label className="el-radio-button">
                <input type="radio" name="sex" className="radio-button" value="保密" checked={props.sex==='保密'} onChange={props.SexChange}/>
                <span className="radio-button-inner">保密</span>
            </label>
        </div>
    )

}



export default connect()(Profile)