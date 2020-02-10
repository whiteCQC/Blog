import React, {Component} from "react";
import {withRouter} from "react-router-dom";
import Axios from "../../axios/axios";

import './blog.css'
import {Nav} from "../header/header";
import BlogLeft from "./blogleft";
import RightUser from "./RightUser";

class Blog extends Component{
    constructor(props) {
        super(props);
        this.state = {
            flag:false,//是否为当前登录用户
            option:0,
            uid:this.props.match.params.uid,
            user:"",
            articles:[],
        }
    }
    componentDidMount (){
        let uid=this.props.match.params.uid
        if(localStorage.getItem("token")!==null && localStorage.getItem("uid")===uid){
            this.setState({
                flag:true
            })
        }
        Axios.get("/blog/personalTest", {
            params: { 'uid': uid }
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    user:data.detail.userInfo,
                    articles:data.detail.articleInfo,
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
                <BlogLeft/>
                <div className="blogRight">

                </div>
                <RightUser user={this.state.user} />
            </div>
        )
    }
}



export default withRouter(Blog)