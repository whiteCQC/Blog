import React, { Component } from 'react';
import Axios from "../../axios/axios";
import {openNotificationWithIcon} from "../notification";
import './article.css'
import {Nav} from "../header/header";
import {followExecute} from "../../redux/interaction/action";
import {connect} from "react-redux";

import {dateTransfer} from '../../tools/tansfer'

class ArticleBody extends Component{
    constructor (props) {
        super(props);
        this.state = {
            article:{},
            author:{},
            articleNum:"",
            totalView:"",
            newArticles:"",
            fanNum:""
        }
        this.handleFollow=this.handleFollow.bind(this)
    }
    componentDidMount (){
        //console.log(this.props)
        let aid=this.props.location.state.articleId
        //console.log("aid:"+aid);
        Axios.get("/article/detailTest", {
            params: { 'aid': aid }
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    article: data.detail.article,
                    author:data.detail.author,
                    totalView:data.detail.totalView,
                    articleNum:data.detail.articleNum,
                    newArticles:data.detail.newArticles,
                    fanNum:data.detail.fanNum
                });
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        })
    }
    handleFollow(){
        //console.log("follow:"+this.state.article.aid)
        if(localStorage.getItem("token")==null){
            alert("请先登录");
        }
        else{
            let followInfo = JSON.stringify({
                followId:localStorage.getItem("uid"),
                followedId:this.state.author.uid
            })
            this.props.dispatch(followExecute(followInfo))
        }
    }

    render() {
        return(
            <div>
                <Nav/>
                <div className="articleBody">
                    {/* 文章作者基本信息 */}
                    <div className="authorInfo">
                        <h3>作者:{this.state.author.uname}</h3>
                        <table border="0">
                            <tbody>
                            <tr>
                                <th>文章数</th>
                                <th>粉丝数</th>
                                <th>访问量</th>
                            </tr>
                            <tr>
                                <td>{this.state.articleNum}</td>
                                <td>{this.state.fanNum}</td>
                                <td>{this.state.totalView}</td>
                            </tr>
                            </tbody>

                        </table>
                        <button className="follow" onClick={this.handleFollow}>
                            关注
                        </button>
                    </div>

                    <div className="articleRight">
                        {/* 文章区域 */}
                        <div className="articlePart">
                            <span className="title">{this.state.article.articleTitle}</span>
                            <button>收藏</button>
                            <div>
                                <span>最后修改于:{dateTransfer(this.state.article.date)}</span>
                                <span  className="viewNum">阅读{this.state.article.viewNum}</span>
                            </div>
                            <div className="articleContent">{this.state.article.articleContent}</div>
                        </div>

                        {/* 评论区域 */}
                        <div id="Comments">
                            <form>
                                <textarea id="CommentTextArea"/>
                                <button id="submitComment">提交</button>
                            </form>
                            <div className="articleComments">

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        )
    }
}

export default connect()(ArticleBody)