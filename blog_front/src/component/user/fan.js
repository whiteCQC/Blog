import React, {Component} from "react";
import {Nav} from "../header/header";
import UserLeftNav from "./left";
import {connect} from "react-redux";
import Axios from "../../axios/axios";

import './fan.css'
import User from "../../image/avatar.jpg"
class Fans extends Component {
    constructor(props) {
        super(props);
        this.state = {
            fans:[]
        }
    }
    componentDidMount () {
        let uid = localStorage.getItem("uid");
        Axios.get("/viewFans",{
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
    render() {
        let tip=''
        if(this.state.fans.length===0){
            tip='暂无粉丝'
        }
        return (
            <div>
                <Nav/>
                <UserLeftNav/>
                <div className="UserRight">
                    <h1 className="UserTitle">我的粉丝</h1>
                    <hr/>
                    <div className="fans">
                        <span>{tip}</span>
                        <ul>
                            {this.state.fans.map((fan,index) =>
                                <li key={fan.uid}>
                                    <img className="avatar" src={User} alt="头像"/>
                                    <span className="fanName">{fan.uname}</span>
                                </li>
                            )}
                        </ul>
                    </div>
                </div>
            </div>
        )
    }
}

export default connect()(Fans)