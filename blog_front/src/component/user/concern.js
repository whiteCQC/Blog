import React, {Component} from "react";
import {Nav} from "../header/header";
import UserLeftNav from "./left";
import {connect} from "react-redux";
import Axios from "../../axios/axios";

import './cocern.css'
import {openNotificationWithIcon} from "../notification";

class Concern extends Component {
    constructor(props) {
        super(props);
        this.state = {
            fans:[]
        }
    }
    componentDidMount () {
        let uid = localStorage.getItem("uid");
        Axios.get("/viewConcernsTest",{
            params:{
                uid:uid
            }
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    fans: data.detail.fans
                });
            }else{
                alert(data.description)
            }
        }).catch( error => {
            alert(error.message)
        })
    }

    FanCancel(followedId){
        let uid = localStorage.getItem("uid");
        Axios.post("/FanCancelTest",{
            followId:uid,
            followedId:followedId
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    fans: data.detail,
                });
                openNotificationWithIcon("success","Success","已取消关注")
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        })
    }

    render() {
        let tip=''
        if(this.state.fans.length===0){
            tip='暂无关注'
        }

        return (
            <div>
                <Nav/>
                <UserLeftNav/>
                <div className="UserRight">
                    <h1 className="UserTitle">我的关注</h1>
                    <hr/>
                    <div className="fans">
                        <span>{tip}</span>
                        <ul>
                            {this.state.fans.map((fan,index) =>
                                <li key={fan.uid}>
                                    <img className="avatar" src="../image/avatar.jpg" alt="头像"/>
                                    <span className="fanName">{fan.uname}</span>
                                    <button className="cancelFollow" onClick={this.FanCancel.bind(this,fan.uid)}>取消关注</button>
                                </li>
                            )}
                        </ul>
                    </div>
                </div>
            </div>
        )
    }
}

export default connect()(Concern)