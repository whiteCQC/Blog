import React, {Component} from "react";
import {withRouter} from "react-router-dom";
import Axios from "../../axios/axios";

import './blog.css'
import {Nav} from "../header/header";
import BlogLeft from "./blogleft";
import RightUser from "./RightUser";
import MyBlog from "./Myblog";
import OtherBlog from "./Otherblog";
import {openNotificationWithIcon} from "../notification";

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
        this.selectArticle=this.selectArticle.bind(this)
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
    selectArticle(articleId){
        let res = "/article/" + articleId
        this.props.history.push(res)
    }
    ArticleDel=(aid)=>{
        Axios.post("/article/delTest",{
            aid:aid
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    articles:data.detail.articleInfo
                })
                openNotificationWithIcon("success","Success","成功删除")
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        })
    }

    render() {
        return(
            <div>
                <Nav/>
                <BlogLeft/>
                <BlogRight flag={this.state.flag} selectArticle={this.selectArticle}
                           articles={this.state.articles} ArticleDel={this.ArticleDel}/>
                <RightUser user={this.state.user} />
            </div>
        )
    }
}

function BlogRight(props){
    if(props.flag){
        return <MyBlog articles={props.articles} selectArticle={props.selectArticle}
                       ArticleDel={props.ArticleDel}
                />
    }else{
        return <OtherBlog articles={props.articles} selectArticle={props.selectArticle} />
    }
}







export default withRouter(Blog)