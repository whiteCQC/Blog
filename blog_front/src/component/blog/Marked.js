import React, {Component} from "react";
import Axios from "../../axios/axios";

import {Spin,Select} from "antd";
import {openNotificationWithIcon} from "../notification";

import './Marked.css'
import SingleArticle from "./singleArticle";
import {confirmAlert} from "react-confirm-alert";

class Marked extends Component{
    constructor(props) {
        super(props);
        this.state={
            markedList:"",
            articles:"",
            curMarkedID:0,
            uid:props.uid,
        }
        this.changeMarked=this.changeMarked.bind(this)
        this.cancelMarked=this.cancelMarked.bind(this)
    }

    componentDidMount (){
        Axios.get("/blog/personal/markedTest", {
            params: { 'uid': this.props.uid }
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    markedList:data.detail
                });
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        });

        this.updateArticlesByMarked(this.props.uid,0);
    }

    changeMarked(value){
        this.setState({
            curMarkedID:value,
        })
        this.updateArticlesByMarked(this.props.uid,value);
    }

    updateArticlesByMarked(uid,mid){
        Axios.get("/blog/personal/marked/articleTest", {
            params: {
                'uid': uid,
                'markid':mid,
            }
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    articles: data.detail.articleList
                });
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        });
    }

    cancelMarked(aid){
        confirmAlert({
            title: '提交确认',
            message: '真的要取消收藏此文章吗？',
            buttons: [
                {
                    label: '确认',
                    onClick: () => {
                        Axios.post("/blog/personal/marked/deleteArticleTest",{
                            uid:this.state.uid,
                            markId:this.state.curMarkedID,
                            aid:aid,
                        }).then(({data}) => {
                            if(data.code === 200){
                                let tempArticles=[];
                                this.state.articles.forEach((a)=>{
                                    if(a.aid!==aid){
                                        tempArticles.push(a)
                                    }

                                });
                                this.setState({
                                    articles:tempArticles
                                });
                                openNotificationWithIcon("success","Success",data.description)
                            }else{
                                openNotificationWithIcon("error","Error",data.description)
                            }
                        }).catch( error => {
                            openNotificationWithIcon("error","Error",error.message)
                        })
                    }
                },
                {
                    label: '放弃',
                    onClick: () => {}
                }
            ]
        });
    }


    render() {
        if(this.state.markedList===""){
            return <Spin/>;
        }

        const { Option } = Select;
        return(
            <div className="blogRight">
                <h1 className="title">我的收藏</h1>
                <Select style={{ width: 200}} placeholder="收藏夹选择"
                        filterOption={(input, option) =>
                            option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                        }
                        onChange={this.changeMarked}
                        className="Marked-selector"
                >
                    {this.state.markedList.map((marked,index) =>
                        <Option value={marked.markId} key={marked.markId}>{marked.markName}</Option>
                    )}
                </Select>
                <button className="del-Marked">删除当前收藏夹</button>
                <button className="new-Marked">新建收藏夹</button>
                <div className="articleList-Marked">
                    <MarkedArticles articles={this.state.articles} selectArticle={this.props.selectArticle}
                                    cancelMarked={this.cancelMarked}
                    />

                </div>
            </div>
        )

    }
}

function MarkedArticles(props){
    if(props.articles==="")
        return <span>暂无收藏</span>;
    else {
        return <ul>
                    {props.articles.map((article,index) =>
                        <li key={article.aid}>
                            <SingleArticle article={article} selectArticle={props.selectArticle}/>
                            <div className="change-article">
                                <button onClick={props.cancelMarked.bind(this,article.aid)}> 取消收藏</button>
                                <button > 移动文章</button>
                            </div>
                        </li>
                    )}
               </ul>
    }
}

export default Marked