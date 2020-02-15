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
        alert(articleId)
    }

    render() {
        return(
            <div>
                <Nav/>
                <BlogLeft/>
                <BlogRight flag={this.state.flag} selectArticle={this.selectArticle}
                           articles={this.state.articles}/>
                <RightUser user={this.state.user} />
            </div>
        )
    }
}

function BlogRight(props){
    if(props.flag){
        return <MyBlog articles={props.articles} selectArticle={props.selectArticle}/>
    }else{
        return <OtherBlog articles={props.articles} selectArticle={props.selectArticle} />
    }
}

class MyBlog extends Component{
    constructor(props) {
        super(props);
        this.state={
            articlesAll:[],
            articles:[],
            articlesPub:[],
            articlesPri:[]
        }
        this.allArticle=this.allArticle.bind(this)
        this.publicArticle=this.publicArticle.bind(this)
        this.privateArticle=this.privateArticle.bind(this)
    }
    componentWillReceiveProps(nextProps){
        this.setState({
            articles:nextProps.articles,
            articlesAll:nextProps.articles,
        })
        let pub=[],pri=[]
        nextProps.articles.map((a)=>{
            if(a.mode==0){
                pub.push(a)
            }else{
                pri.push(a)
            }
        })
        this.setState({
            articlesPub:pub,
            articlesPri:pri
        })
    }

    allArticle(){
        this.setState({
            articles:this.state.articlesAll
        })
    }
    publicArticle(){
        this.setState({
            articles:this.state.articlesPub
        })
    }
    privateArticle(){
        this.setState({
            articles:this.state.articlesPri
        })
    }
    render() {
        console.log()
        return(
            <div className="blogRight">
                <div className="buttonHeaders">
                    <button id="_all" onClick={this.allArticle}>全部</button>
                    <button id="_pub" onClick={this.publicArticle}>公开</button>
                    <button id="_pri" onClick={this.privateArticle}>私密</button>
                </div>
                <div className="blogArticles">
                    <ul>
                        {this.state.articles.map((article,index) =>
                            <li key={article.aid} onClick={this.props.selectArticle.bind(this,article.aid)}>
                                <SingleArticle article={article}  />
                            </li>
                        )}
                    </ul>
                </div>
            </div>
        )
    }
}

class OtherBlog extends Component{
    render() {
        return(
            <div>

            </div>
        )
    }
}

const SingleArticle = ({article},index)=>(
    <div className="hotList">
        <h2>
            {article.articleTitle}
        </h2>
        <p><span>{article.articleContent}</span></p>
        <div className="viewNum">
            <img src="../image/eye.jpg" alt="浏览人数"/>{article.viewNum}
        </div>
    </div>
)


export default withRouter(Blog)