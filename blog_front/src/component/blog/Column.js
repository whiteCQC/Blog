import React, {Component} from "react";
import {Select,Spin} from "antd";
import SingleArticle from "./singleArticle";
import Axios from "../../axios/axios";
import {openNotificationWithIcon} from "../notification";

class Column extends Component{
    constructor(props) {
        super(props);
        this.state={
            ColumnList:"",
            columnChoose:-1,
            columnIndex:0,
            articles:[],
        }
        this.changeColumn=this.changeColumn.bind(this)
        this.getArticlesByColumn=this.getArticlesByColumn.bind(this)
    }

    componentDidMount() {
        Axios.get("/blog/personal/Columns", {
            params: { 'uid': this.props.uid }
        }).then(({data}) => {
            if(data.code === 200){
                this.setState({
                    ColumnList:data.detail,
                    columnChoose:data.detail[0].spColId,
                });
                this.getArticlesByColumn(data.detail[0].spColId);
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        });


    }

    getArticlesByColumn(id){
        Axios.get("/blog/personal/ColumnArticles", {
            params: { 'Sp_col': id }
        }).then(({data}) => {
            if(data.code === 200){
                //console.log(data.detail)
                this.setState({
                    articles:data.detail,
                });
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        });
    }

    changeColumn(value){
        this.setState({
            columnChoose:value
        })
        this.getArticlesByColumn(value)
    }

    render() {
        if(this.state.ColumnList===""){
            return <Spin/>
        }
        //console.log(this.state.ColumnList)
        const { Option } = Select;
        return(
            <div className="blogRight">
                <h1 className="title">我的专栏</h1>
                <Select style={{ width: 200}} placeholder="专栏选择"
                        filterOption={(input, option) =>
                            option.children.toLowerCase().indexOf(input.toLowerCase()) >= 0
                        }
                        onChange={this.changeColumn}
                        className="Marked-selector"
                >
                    {this.state.ColumnList.map((column,index) =>
                        <Option value={column.spColId} key={column.spColId}>{column.spColName}</Option>
                    )}
                </Select>
                <button className="del-Marked">删除当前专栏</button>
                <button className="new-Marked">新建专栏</button>
                <div className="articleList-Marked">
                    <ColumnArticles  articles={this.state.articles}
                                     selectArticle={this.props.selectArticle}
                    />
                </div>

            </div>
        )

    }
}

function ColumnArticles(props){
    //console.log(props.articles)
    if(props.articles===""||props.articles===undefined||!props.articles)
        return <span className="myTips">该专栏暂无文章</span>;
    else {
        return <ul>
            {props.articles.map((article,index) =>
                <li key={article.aid}>
                    <SingleArticle article={article} selectArticle={props.selectArticle}/>
                    <div className="change-article">
                        <button>移动文章</button>
                    </div>
                </li>
            )}
        </ul>
    }
}


export default Column