import React, { Component } from 'react';
import Axios from "../../axios/axios";
import {Nav} from "../header/header";

import './writeBlog.css'
import {openNotificationWithIcon} from "../notification";
import Spin from "antd/es/spin";

class WriteBlog extends Component{
    constructor(props) {
        super(props);
        this.state={
            type:0,//文章类型
            mode:0,//发布模式
            Sp_col:0,//专栏
            text:'',
            title:'',
            change:false,
        }

        this.submitText=this.submitText.bind(this)
    }
    componentDidMount (){
        if(typeof this.props.location.state!=='undefined'){
            const aid=this.props.location.state.aid
            Axios.get("/article/simpleTest", {
                params: { 'aid': aid }
            }).then(({data}) => {
                if(data.code === 200){
                    this.setState({
                        title:data.detail.article.articleTitle,
                        mode: data.detail.article.mode,
                        type: data.detail.article.type,
                        Sp_col: data.detail.article.spColId,
                        text: data.detail.article.articleContent,
                        change:true,
                    });
                }else{
                    openNotificationWithIcon("error","Error",data.description)
                }
            }).catch( error => {
                openNotificationWithIcon("error","Error",error.message)
            })

        }

    }


    submitText(){
        if(contentNotNull(this.state.title,this.state.text)){
            Axios.post("/article/create",{
                uid:localStorage.getItem("uid"),
                type:this.state.type,
                mode:this.state.mode,
                spColId:this.state.Sp_col,
                articleContent:this.state.text,
                articleTitle:this.state.title,
                viewNum:0
            }).then(({data}) => {
                if(data.code === 200){
                    alert("发布成功")

                    //跳转个人博客
                }else{
                    alert(data.description)
                }
            }).catch( error => {
                alert(error.message)
            })
        }

    }

    changeText(){
        if(contentNotNull(this.state.title,this.state.text)){
            Axios.post("/article/modify",{
                uid:localStorage.getItem("uid"),
                aid:this.props.location.state.aid,
                type:this.state.type,
                mode:this.state.mode,
                spColId:this.state.Sp_col,
                articleContent:this.state.text,
                articleTitle:this.state.title,
            }).then(({data}) => {
                if(data.code === 200){
                    alert("修改成功")

                    //跳转个人博客
                }else{
                    alert(data.description)
                }
            }).catch( error => {
                alert(error.message)
            })
        }

    }

    typeChange = (e)=>{
        this.setState({
            type:e.target.value
        })
    }

    modeChange = (e)=>{
        this.setState({
            mode:e.target.value
        })
    }
    Sp_col_Change = (e)=>{
        this.state({
            Sp_col:e.target.value
        })
    }
    textChange= (e)=>{
        this.setState({
            text:e.target.value
        })
    }
    titleChange= (e)=>{
        this.setState({
            title:e.target.value
        })
    }

    render() {
        if(typeof this.props.location.state!=='undefined'){
            if(this.state.title==='')
                return <Spin/>
        }
        return(
            <div>
                <Nav/>
                <div id="text">
                    <input type="text" id="textTitle" maxLength="50"
                           placeholder="请输入文章标题" onChange={this.titleChange} defaultValue={this.state.title}/>

                    <div id="textContent">
                        <textarea placeholder="文章内容" onChange={this.textChange} defaultValue={this.state.text}/>
                    </div>
                    <div className="textSelect" >
                        <span>文章类型:</span>
                        <select defaultValue={this.state.type} onChange={this.typeChange} >
                            <option value ="0">原创</option>
                            <option value ="1">转载</option>
                        </select>
                    </div>
                    <div className="textSelect">
                        <span>发布形式:</span>
                        <select defaultValue={this.state.mode} onChange={this.modeChange}>
                            <option value ="0">公开</option>
                            <option value ="1">私密</option>
                        </select>

                    </div>
                    <div className="textSelect">
                        <span>专栏归类:</span>
                        <select defaultValue={this.state.Sp_col} onChange={this.Sp_col_Change}>
                            <option value ="0">未分类</option>
                        </select>

                    </div>
                    <SubmitButton submitText={this.submitText}
                                  changeText={this.changeText}
                                  change={this.state.change}
                    />
                </div>

            </div>
        )
    }
}

function contentNotNull(title,text){
    if(!title.trim()){
        alert("标题不能为空")
        return false;
    }else if(!text.trim()){
        alert("文章内容不能为空")
        return false;
    }else{
        return true;
    }
}

function SubmitButton(props){
    if(!props.change){
        return <button className="submitText" onClick={props.submitText}>发布文章</button>
    }else{
        return <button className="submitText" onClick={props.changeText}>确认保存</button>
    }
}

export default WriteBlog