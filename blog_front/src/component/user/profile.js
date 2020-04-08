import React, { Component } from 'react';
import Axios from "../../axios/axios";
import {connect} from "react-redux";
import UserLeftNav from "./left";
import {Nav} from "../header/header";
import {dateTransferSimple} from "../../tools/tansfer"
import { DatePicker } from 'antd';
import {openNotificationWithIcon} from "../notification/index";
import moment from 'moment';
import './profile.css'

class Profile extends Component{
    constructor (props) {
        super(props);
        this.state = {
            user:"",
            curName:"",
            curSex:"",
            curBirth:"",
        }
        this.ChangeInfo=this.ChangeInfo.bind(this)
    }
    componentDidMount (){
        let uid=localStorage.getItem("uid");
        Axios.post("/userInfo",{
            uid:uid
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    user: data.detail,
                    curName:data.detail.uname,
                    curSex:data.detail.gender,
                    curBirth:dateTransferSimple(data.detail.birth)
                });
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        })
    }

    ChangeInfo(){
        if(this.state.curName===""||this.state.curBirth===""){
            openNotificationWithIcon("error","Error","信息不能为空!")
        }else{
            Axios.post("/userInfoChange",{
                uid:this.state.user.uid,
                uname:this.state.curName,
                gender:this.state.curSex,
                birth:new Date(this.state.curBirth.replace(/-/g, '/'))
            }).then(({data}) => {
                if(data.code === 200){
                    this.setState({
                        user: data.detail,
                    });

                    openNotificationWithIcon("success","Success","保存成功")
                }else{
                    openNotificationWithIcon("error","Error",data.description)
                }
            }).catch( error => {
                openNotificationWithIcon("error","Error",error.message)
            })
        }
    }
    ChangeName=(e)=>{
        this.setState({
            curName:e.target.value
        })
    }
    ChangeSex=(e)=>{
        this.setState({
            curSex:e.target.value
        })
    }
    ChangeBirth=(value, dateString)=> {
        this.setState({
            curBirth:dateString
        })
    }

    render() {
        return(
            <div>
                <Nav/>
                <UserLeftNav/>
                <PersonalInfo toChange={this.state.toChange}
                              curName={this.state.curName} ChangeName={this.ChangeName}
                              curSex={this.state.curSex} ChangeSex={this.ChangeSex}
                              curBirth={this.state.curBirth} ChangeBirth={this.ChangeBirth}
                              user={this.state.user} ChangeInfo={this.ChangeInfo}
                />
            </div>
            )
    }
}

function PersonalInfo(props){
    const dateFormat = 'YYYY-MM-DD';

    let birth=props.curBirth===""?null:moment(props.curBirth, dateFormat)

    return(
        <div className="UserRight">
            <h1 className="UserTitle">个人资料</h1>
            <hr/>
            <div className="userInfo">
                昵称:<input defaultValue={props.curName} placeholder="你的昵称" onChange={props.ChangeName}/><br/><br/>
                性别:<Sex sex={props.curSex} SexChange={props.ChangeSex}/>
                <br/><br/>
                邮箱:<span className="email-info">{props.user.email}</span><br/><br/>
                生日:<DatePicker className="birth-date-picker"
                               value={birth}
                               format={dateFormat}
                               onChange={props.ChangeBirth}
                    />
                <br/>

            </div>
            <button id="saveProfile" onClick={props.ChangeInfo}>保存信息</button>
        </div>
    )

}

function Sex(props){
    const unCheckedStyle={
        border:'1px solid #bfcbd9',
        background:'#f4f4f4',
        color:'#717171',
    };
    const CheckedStyle={
        border:'1px solid #22a1d6',
        background:'#22a1d6',
        color:'#fff'
    }
    return(
        <div className="radio-group">
            <label className="el-radio-button" >
                <input type="radio" name="sex" className="radio-button" value="男" checked={props.sex==='男'} onChange={props.SexChange}/>
                <span className="radio-button-inner" style={props.sex==='男'?CheckedStyle:unCheckedStyle}>男</span>
            </label>
            <label className="el-radio-button">
                <input type="radio" name="sex" className="radio-button" value="女" checked={props.sex==='女'} onChange={props.SexChange}/>
                <span className="radio-button-inner" style={props.sex==='女'?CheckedStyle:unCheckedStyle}>女</span>
            </label>
            <label className="el-radio-button">
                <input type="radio" name="sex" className="radio-button" value="保密" checked={props.sex==='保密'} onChange={props.SexChange}/>
                <span className="radio-button-inner" style={props.sex==='保密'?CheckedStyle:unCheckedStyle}>保密</span>
            </label>
        </div>
    )

}



export default connect()(Profile)