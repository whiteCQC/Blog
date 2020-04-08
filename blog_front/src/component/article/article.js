import React, { Component } from 'react';
import Axios from "../../axios/axios";
import {openNotificationWithIcon} from "../notification";
import './article.css'
import {Nav} from "../header/header";
import {followExecute} from "../../redux/interaction/action";
import {connect} from "react-redux";

import {dateTransfer, dateTransferSimple, NumberTransferForLargeNum} from '../../tools/tansfer'
import {Modal,Spin,Radio,Comment,Avatar} from "antd";

class ArticleBody extends Component{
    constructor (props) {
        super(props);
        this.state = {
            article: {},
            author: {},
            articleNum: "",
            totalView: "",
            newArticles: "",
            fanNum: "",

            comments:[],
            commentToSubmit:"",

            self_article: false,
            modalFavoriteVisible: false,
            markedList: [],
            markedId:0,
        }

        this.handleFollow=this.handleFollow.bind(this)
        this.handleStore=this.handleStore.bind(this)
        this.confirmMarked=this.confirmMarked.bind(this)
        this.CommentChange=this.CommentChange.bind(this)
        this.submitComment=this.submitComment.bind(this)
    }
    componentDidMount (){
        //console.log(this.props)
        let aid=this.props.match.params.aid;
        //console.log("aid:"+aid);
        let loginUid = localStorage.getItem("uid")!==null? localStorage.getItem("uid") :-1 ;

        Axios.get("/article/detail", {
            params: { 'aid': aid }
        }).then(({data}) => {
            console.log(data.detail)
            if(data.code === 200){
                this.setState({
                    article: data.detail.article,
                    author:data.detail.author,
                    totalView:data.detail.author.totalView,
                    articleNum:data.detail.author.articleNum,
                    newArticles:data.detail.author.newArticles,
                    fanNum:data.detail.author.fanNum,
                    self_article:loginUid===data.detail.article.uid,
                });
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        });

        Axios.get("/getComments", {
            params: { 'aid': aid }
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    comments: data.detail,
                });
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        });

        if(localStorage.getItem("uid")!==null){
            Axios.get("/blog/personal/markedTest", {
                params: { 'uid': loginUid }
            }).then(({data}) => {
                if(data.code === 200){
                    this.setState({
                        markedList:data.detail,
                    });
                }else{
                    openNotificationWithIcon("error","Error",data.description)
                }
            })
        }
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

    handleStore(){
        if(localStorage.getItem("token")==null){
            alert("请先登录");
        }else{
            this.setState({
                modalFavoriteVisible:true,
            })
        }
    }
    changeMarkedSelect=(e)=>{
        this.setState({
            markedId:e.target.value
        })
    }

    confirmMarked(){
        //console.log(localStorage.getItem("uid"),this.state.markedId,this.state.article.aid)
        Axios.post("/blog/personal/marked/addArticle",{
            uid:localStorage.getItem("uid"),
            markId:this.state.markedId,
            aid:this.state.article.aid
        }).then(({data}) => {
            if(data.code === 200){
                openNotificationWithIcon("success","Success",data.description)
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        })
    }

    CommentChange(e){
        this.setState({
            commentToSubmit:e.target.value
        })
    }
    submitComment(){
        let content=this.state.commentToSubmit;
        if(content.length<6)
            openNotificationWithIcon("error","Error","评论内容过短");
        else if(content.length>100)
            openNotificationWithIcon("error","Error","评论内容过长");
        else{
            Axios.post("/commentSubmit",{
                uid:localStorage.getItem("uid"),
                commentContent:content,
                aid:this.state.article.aid
            }).then(({data}) => {
                if(data.code === 200){
                    let comments=this.state.comments
                    comments.push(data.detail);
                    this.setState({
                        commentContent:"",
                        comments:comments
                    });
                    openNotificationWithIcon("success","Success",data.description)
                }else{
                    openNotificationWithIcon("error","Error",data.description)
                }
            }).catch( error => {
                openNotificationWithIcon("error","Error",error.message)
            })
        }
    }

    render() {
        if(this.state.totalView===""){
            return <Spin/>
        }
        const radioStyle = {
            display: 'block',
            height: '40px',
            lineHeight: '40px',
        };
        return(
            <div>
                <Nav/>
                <div className="articleBody">
                    {/* 文章作者基本信息 */}
                    <div className="authorInfo">
                        <h3>作者:{this.state.author.name}</h3>
                        <table border="0" className="UserInfoTable" cellPadding="10">
                            <tbody>
                            <tr>
                                <th>文章数</th>
                                <th>粉丝数</th>
                                <th>访问量</th>
                            </tr>
                            <tr>
                                <td>{this.state.articleNum}</td>
                                <td>{this.state.fanNum}</td>
                                <td>{NumberTransferForLargeNum(this.state.totalView)}</td>
                            </tr>
                            </tbody>

                        </table>
                        <button className="follow" onClick={this.handleFollow} hidden={this.state.self_article}>
                            关注
                        </button>
                    </div>

                    <div className="articleRight">
                        {/* 文章区域 */}
                        <div className="articlePart">
                            <span className="article-title">{this.state.article.articleTitle}</span>
                            <button className="button-store" onClick={this.handleStore}
                                    hidden={this.state.self_article}>
                                收藏
                            </button>
                            <div>
                                <span>最后修改于:{dateTransfer(this.state.article.date)}</span>
                                <span  className="viewNum">阅读{this.state.article.viewNum}</span>
                            </div>
                            <div className="articleContent">{this.state.article.articleContent}</div>
                        </div>

                        {/* 评论区域 */}
                        <div id="Comments">
                                <textarea id="CommentTextArea" placeholder="请自觉遵守互联网相关政策法规，严禁发布色情、暴力、反动的言论。"
                                          onChange={this.CommentChange}
                                />
                                <button id="submitComment" onClick={this.submitComment}>发表评论</button>
                            <div className="articleComments">
                                {
                                    this.state.comments.map((comment,index)=>
                                        <Comment author={<p>{comment.userName}</p>}
                                                 avatar={
                                                     <Avatar
                                                         src="../image/avatar.jpg"
                                                         alt={comment.userName}
                                                     />
                                                 }
                                                 content={
                                                     <p>
                                                         {comment.commentContent}
                                                     </p>
                                                         }
                                                 datetime={<p>{dateTransferSimple(comment.commentDate)}</p>}
                                                 key={index}
                                        >


                                        </Comment>
                                    )
                                }
                            </div>
                        </div>
                    </div>
                </div>
                <Modal visible={this.state.modalFavoriteVisible} title="收藏夹选择"
                       onCancel={()=>{
                           this.setState({modalFavoriteVisible: false})
                       }}
                       onOk={this.confirmMarked}
                >
                    <div className="group-list">
                        <Radio.Group defaultValue={0}>
                            {this.state.markedList.map((marked)=>
                                <Radio style={radioStyle}
                                    value={marked.markId} onChange={this.changeMarkedSelect} key={marked.markId}>
                                    {marked.markName}
                                </Radio>
                            )}
                        </Radio.Group>
                    </div>
                </Modal>
            </div>

        )
    }
}


export default connect()(ArticleBody)