import React, {Component} from "react";
import Axios from "../../axios/axios";

import {Spin, Select, Popconfirm, Modal, Radio} from "antd";
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
            curMarkedID:1,
            uid:props.uid,

            changeMarked:false,
            changeMarkedId:1,
            changeAid:"",

            newMarked:false,
            newMarkedName:"",
        }
        this.changeMarked=this.changeMarked.bind(this);
        this.cancelMarked=this.cancelMarked.bind(this);
        this.delMarked=this.delMarked.bind(this);
        this.handleAdd=this.handleAdd.bind(this);
        this.confirmNewMarked=this.confirmNewMarked.bind(this);
        this.moveArticleMarked=this.moveArticleMarked.bind(this);
        this.confirmMove=this.confirmMove.bind(this);
    }

    componentDidMount (){
        Axios.get("/blog/personal/marked", {
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

        this.updateArticlesByMarked(this.props.uid,1);
    }

    changeMarked(value){
        this.setState({
            curMarkedID:value,
        })
        this.updateArticlesByMarked(this.props.uid,value);
    }

    updateArticlesByMarked(uid,mid){
        Axios.get("/blog/personal/marked/article", {
            params: {
                'uid': uid,
                'markid':mid,
            }
        }).then(({data}) => {
            if(data.code === 200){//console.log(data.detail)
                this.setState({
                    articles: data.detail
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
                        Axios.post("/blog/personal/marked/deleteArticle",{
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

    delMarked(){
        if(this.state.curMarkedID===1){
            openNotificationWithIcon("error","Error","默认文件夹无法删除")
        }else{
            Axios.post("/blog/personal/marked/deleteMarked",{
                uid:this.state.uid,
                markId:this.state.curMarkedID,
            }).then(({data}) => {
                if(data.code === 200){
                    this.setState({
                        curMarkedID:1
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

    handleAdd(){
        this.setState({
            newMarked:true,
        })
    }

    confirmNewMarked(){
        let name=this.state.newMarkedName;
        if(name.length<2)
            openNotificationWithIcon("error","Error","收藏夹名过短")
        else if(name.length>10)
            openNotificationWithIcon("error","Error","收藏夹名过长")
        else{
            Axios.post("/blog/personal/marked/addMarked",{
                uid:this.state.uid,
                markName:name,
            }).then(({data}) => {
                if(data.code === 200){
                    let tempList=this.state.markedList
                    tempList.push(data.detail)
                    this.setState({
                        markedList:tempList,
                        newMarked:false,
                    })
                    openNotificationWithIcon("success","Success",data.description)
                }else{
                    openNotificationWithIcon("error","Error",data.description)
                }
            }).catch( error => {
                openNotificationWithIcon("error","Error",error.message)
            })
        }

    }

    newMarkedName=(e)=>{
        this.setState({
            newMarkedName:e.target.value
        })
    }

    moveArticleMarked(aid){
        this.setState({
            changeMarked:true,
            changeAid:aid,
        })
    }

    changeMarkedSelect(mid){
        this.setState({
            changeMarkedId:mid
        })
    }

    confirmMove(){
        if(this.state.curMarkedID===this.state.changeMarkedId){
            openNotificationWithIcon("error","Error","收藏夹没有变化")
        }
        else{
            Axios.post("/blog/personal/marked/moveArticle",{
                uid:this.state.uid,
                aid:this.state.changeAid,
                oldMarkedId:this.state.curMarkedID,
                newMarkedId:this.state.changeMarkedId,
            }).then(({data}) => {
                if(data.code === 200){
                    let tempList=this.state.markedList
                    tempList.push(data.detail)
                    this.setState({
                        markedList:tempList
                    })
                    openNotificationWithIcon("success","Success",data.description)
                }else{
                    openNotificationWithIcon("error","Error",data.description)
                }
            }).catch( error => {
                openNotificationWithIcon("error","Error",error.message)
            })
            this.setState({changeMarked: false})
        }

    }

    render() {
        if(this.state.markedList===""){
            return <Spin/>;
        }

        const { Option } = Select;
        const radioStyle = {
            display: 'block',
            height: '40px',
            lineHeight: '40px',
        };
        //console.log(this.state.articles)
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
                <Popconfirm title="确定删除该收藏夹吗？" onConfirm={this.delMarked}
                            okText="确认"
                            cancelText="取消"
                >
                    <button className="del-Marked">删除当前收藏夹</button>
                </Popconfirm>

                <button className="new-Marked" onClick={this.handleAdd}>新建收藏夹</button>
                <div className="articleList-Marked">
                    <MarkedArticles articles={this.state.articles} selectArticle={this.props.selectArticle}
                                    cancelMarked={this.cancelMarked}
                                    moveArticleMarked={this.moveArticleMarked}
                    />

                </div>

                <Modal visible={this.state.changeMarked} title="收藏夹选择"
                       onCancel={()=>{
                           this.setState({changeMarked: false})
                       }}
                       onOk={this.confirmMove}
                >
                    <div className="group-list">
                        <Radio.Group defaultValue={0}>
                            {this.state.markedList.map((marked)=>
                                <Radio style={radioStyle}
                                       value={marked.markId} onChange={this.changeMarkedSelect.bind(this,marked.markId)}
                                       key={marked.markId}>
                                    {marked.markName}
                                </Radio>
                            )}
                        </Radio.Group>
                    </div>
                </Modal>

                <Modal visible={this.state.newMarked} title="新建收藏夹"
                       onCancel={()=>{
                           this.setState({newMarked: false})
                       }}
                       onOk={this.confirmNewMarked}
                >
                    <input placeholder="文件夹名称" type="text"
                           onChange={this.newMarkedName}
                    />
                </Modal>
            </div>
        )

    }
}

function MarkedArticles(props){
    //console.log(props)
    if(props.articles===""||props.articles===undefined)
        return <span className="myTips">当前收藏夹暂无文章</span>;
    else {
        return <ul>
                    {props.articles.map((article,index) =>
                        <li key={article.aid}>
                            <SingleArticle article={article} selectArticle={props.selectArticle}/>
                            <div className="change-article">
                                <button onClick={props.cancelMarked.bind(this,article.aid)}> 取消收藏</button>
                                <button onClick={props.moveArticleMarked.bind(this,article.aid)}> 移动文章</button>
                            </div>
                        </li>
                    )}
               </ul>
    }
}

export default Marked