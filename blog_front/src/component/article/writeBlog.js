import React, { Component } from 'react';
import Axios from "../../axios/axios";
import {Nav} from "../header/header";

import './writeBlog.css'

class WriteBlog extends Component{
    constructor(props) {
        super(props);
        this.state={
            type:0,
            mode:0,
            Sp_col:0,
            text:'',
            title:'',
        }

        this.submitText=this.submitText.bind(this)
    }
    submitText(){
        if(!this.state.title.trim()){
            alert("标题不能为空")
        }else if(!this.state.text.trim()){
            alert("文章内容不能为空")
        }
        else{
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
        return(
            <div>
                <Nav/>
                <div id="text">
                    <input type="text" id="textTitle" maxLength="50"
                           placeholder="请输入文章标题" onChange={this.titleChange}/>

                    <div id="textContent">
                        <textarea placeholder="文章内容" onChange={this.textChange}/>
                    </div>
                    <div className="textSelect" >
                        <span>文章类型:</span>
                        <select defaultValue="0" onChange={this.typeChange}>
                            <option value ="0">原创</option>
                            <option value ="1">转载</option>
                        </select>
                    </div>
                    <div className="textSelect">
                        <span>发布形式:</span>
                        <select defaultValue="0" onChange={this.modeChange}>
                            <option value ="0">公开</option>
                            <option value ="1">私密</option>
                        </select>

                    </div>
                    <div className="textSelect">
                        <span>专栏归类:</span>
                        <select defaultValue="0" onChange={this.Sp_col_Change}>
                            <option value ="0">未分类</option>
                        </select>

                    </div>
                    <button id="submitText" onClick={this.submitText}>发布文章</button>
                </div>

            </div>
        )
    }
}

export default WriteBlog