import React, {Component} from "react";
import {Nav} from "../header/header";
import UserLeftNav from "./left";
import {connect} from "react-redux";
import Axios from "../../axios/axios";

import './cocern.css'
import {openNotificationWithIcon} from "../notification";
import User from "../../image/avatar.jpg"
class Concern extends Component {
    constructor(props) {
        super(props);
        this.state = {
            fans:[]
        }
    }
    componentDidMount () {
        let uid = localStorage.getItem("uid");
        Axios.get("/viewConcerns",{
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
        console.log(uid,followedId)
        Axios.post("/FanCancel",{
            followerId:uid,
            authorId:followedId
        }).then(({data}) => {
            if(data.code === 200){
                openNotificationWithIcon("success","Success","已取消关注")
                setTimeout(()=>window.location.reload(),2000);
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
        console.log(this.state.fans)
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
                                <li key={fan.authorId}>
                                    <img className="avatar" src={User} alt="头像"/>
                                    <span className="fanName">{fan.authorName}</span>
                                    <button className="cancelFollow" onClick={this.FanCancel.bind(this,fan.authorId)}>取消关注</button>
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