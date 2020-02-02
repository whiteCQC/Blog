import React, { Component } from 'react';
import Axios from "../../axios/axios";
import {openNotificationWithIcon} from "../notification";

class Article extends Component{
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
    }
    componentDidMount (){
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
    render() {
        return(
            <div>
                {console.log(this.state)}
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
                    <button>
                        关注
                    </button>
                </div>

                <div className="articleRight">
                    <div className="articlePart">
                        <span>{this.state.article.articleTitle}</span>
                        <span>最后修改于:{this.state.article.date}</span>
                        <span>阅读{this.state.article.viewNum}</span>
                        <span>{this.state.article.articleContent}</span>
                    </div>
                    <button>收藏</button>
                    <div className="authorBottom">
                        <span>作者:{this.state.author.uname}</span>
                        <span>文章访问量{this.state.article.viewNum}</span>
                        <button>关注</button>
                    </div>

                    <div className="Comments">
                        <form>
                            <textarea></textarea>
                            <button>提交</button>
                        </form>

                    </div>
                </div>
            </div>
        )
    }
}

export default Article