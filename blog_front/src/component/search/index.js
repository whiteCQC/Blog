/* 搜索结果页面 */

import React, {Component} from "react";
import {Nav} from "../header/header";
import {connect} from "react-redux";
import Axios from "../../axios/axios";
import {withRouter} from "react-router-dom";

import './index.css'
import PageComponent from "../article/pageComponent";

class SearchResult extends Component{
    constructor (props) {
        super(props);
        this.state = {
            indexList : [], //获取数据的存放数组
            current: 1, //当前页码
            pageSize:5, //每页显示的条数5条
            goValue:'',
            totalPage:'',//总页数
            keywords:''
        }
        this.selectArticle = this.selectArticle.bind(this);
    }
    componentDidMount (){
        let keywords=this.props.match.params.keywords
        this.setState({
            keywords:keywords
        })
        console.log(keywords)
        this.pageClickWithKeywords(keywords,1)

    }
    pageClickWithKeywords(keywords,pageNum){
        console.log("pageClick:"+pageNum)
        Axios.get("/article/searchTest3",{
            params:{
                keywords:keywords,
                pageNum:pageNum
            }
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    indexList: data.detail.list,
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

    //点击翻页
    pageClick(pageNum) {
        console.log("pageClick:"+pageNum)
        Axios.get("/article/searchTest3",{
            params:{
                keywords:this.state.keywords,
                pageNum:pageNum
            }
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    indexList: data.detail.list,
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
        //alert(cur+"==="+_this.state.totalPage)
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
    selectArticle(articleId){
        let res = "/article/" + articleId
        this.props.history.push(res)
    }

    render() {
        return(
            <div>
                <Nav/>

                <div id="searchArticle">
                    <ul>
                        {this.state.indexList.map((article,index) =>
                            <li key={article.aid} onClick={this.selectArticle.bind(this,article.aid)}>
                                <SingleSearch article={article}  />
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

const SingleSearch = ({article},index)=>(
    <div className="searchList">
        <h2>
            {article.articleTitle}
        </h2>
        <p><span>{article.articleContent}</span></p>
        <div className="viewNum">
            <img src="../image/eye.jpg" alt="浏览人数"/>{article.viewNum}
        </div>
    </div>
)

export default connect()(withRouter(SearchResult))