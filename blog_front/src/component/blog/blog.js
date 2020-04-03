import React, {Component} from "react";
import {withRouter} from "react-router-dom";
import Axios from "../../axios/axios";

import './blog.css'
import {Nav} from "../header/header";
import BlogLeft from "./blogleft";
import RightUser from "./RightUser";
import MyBlog from "./Myblog";
import OtherBlog from "./Otherblog";
import Marked from "./Marked";
import Column from "./Column";
import {openNotificationWithIcon} from "../notification";

import {Spin} from "antd";


class Blog extends Component{
    constructor(props) {
        super(props);
        this.state = {
            flag:false,//是否为当前登录用户
            option:0,
            uid:this.props.match.params.uid,
            user:"",
            articles:[],

            blogChoice:0,//左边菜单的选择
        }
        this.selectArticle=this.selectArticle.bind(this)
        this.BlogContentChoiceChange=this.BlogContentChoiceChange.bind(this)
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
        let res = "/article/" + articleId;
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

    BlogContentChoiceChange(choice){
        this.setState({
            blogChoice:choice
        })

    }
    render() {
        if(this.state.user==='')
            return <Spin/>;
        return(
            <div>
                <Nav/>
                <BlogLeft BlogContentChoiceChange={this.BlogContentChoiceChange} />
                <BlogRight blogChoice={this.state.blogChoice}
                           flag={this.state.flag} selectArticle={this.selectArticle}
                           articles={this.state.articles} ArticleDel={this.ArticleDel}
                           uid={this.state.uid}
                           history={this.props.history}
                />
                <RightUser user={this.state.user} />
            </div>
        )
    }
}

function BlogRight(props){
    switch (props.blogChoice) {
        case 0:    if(props.flag){
                        return <MyBlog articles={props.articles} selectArticle={props.selectArticle}
                                       ArticleDel={props.ArticleDel} history={props.history}
                        />
                    }else{
                        return <OtherBlog articles={props.articles} selectArticle={props.selectArticle} />
                    }
        case 1:     return <Marked uid={props.uid}/>

        case 2:     return <Column uid={props.uid}/>

        default:    return <div>出错了</div>
    }

}







export default withRouter(Blog)