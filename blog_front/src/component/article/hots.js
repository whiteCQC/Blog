import React, { Component } from 'react';
import Axios from "../../axios/axios";
import {withRouter} from "react-router-dom";

import './hots.css';
import PageComponent from "../page/pageComponent";
import {dateTransfer} from "../../tools/tansfer";
import {Spin} from "antd";

import ViewNum from "../../image/eye.jpg"

class Hots extends Component{
    constructor (props) {
        super(props);
        this.state = {
            articles : [], //获取数据的存放数组
            current: 1, //当前页码
            pageSize:5, //每页显示的条数5条
            goValue:'',
            totalPage:'',//总页数
        }
        this.selectHot = this.selectHot.bind(this);
    }
    componentDidMount (){
        this.pageClick(1)
    }
    selectHot(articleId){
        //console.log(articleId){ pathname: "/about", state: { id } }
        let res = "/article/" + articleId
        this.props.history.push(res)

    }
    //点击翻页
    pageClick(pageNum) {
        Axios.get("/article/hot",{
            params:{
                pageNum:pageNum
            }
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    articles: data.detail.list,
                    totalPage:data.detail.total
                });
            }else{
                alert(data.description)
            }
        }).catch( error => {
            alert(error.message)
        })
        if (pageNum !== this.state.current) {
            this.setState({current : pageNum})
        }
    }

    goNext() {
        let _this = this;
        let cur = this.state.current;
        if (cur < this.state.totalPage) {
            _this.pageClick(cur + 1);
        }
    }
    goPrevClick(){
        let _this = this;
        let cur = this.state.current;
        if(cur > 1){
            _this.pageClick(cur - 1);
        }
    }
    render(){
        if(this.state.totalPage==='')
            return <Spin/>
        return(
            <div>
                <div id="hotArticle">
                    <h1>热门推荐</h1>
                    <ul>
                        {this.state.articles.map((article,index) =>
                            <li key={article.aid} onClick={this.selectHot.bind(this,article.aid)}>
                                <SingleHot article={article}  />
                            </li>
                        )}
                    </ul>
                </div>
                <div className="pages">
                    <PageComponent  current={this.state.current}
                                    totalPage={this.state.totalPage}
                                    goValue={this.state.goValue}
                                    pageClick={this.pageClick.bind(this)}
                                    goPrev={this.goPrevClick.bind(this)}
                                    goNext={this.goNext.bind(this)}/>
                </div>
            </div>


        )
    }
}

const SingleHot = ({article},index)=>(
        <div className="hotList">
            <h2>
                <span className="hotLogo">HOT</span>
                {article.articleTitle}
            </h2>
            <p><span>{dateTransfer(article.date)}</span></p>
            <div className="viewNum">
                <img src={ViewNum} alt="浏览人数"/>{article.viewNum}
            </div>
        </div>
)

export default withRouter(Hots);