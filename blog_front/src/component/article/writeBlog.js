import React, { Component } from 'react';
import Axios from "../../axios/axios";
import {Nav} from "../header/header";

import './writeBlog.css'

class WriteBlog extends Component{
    constructor(props) {
        super(props);
        this.state={
            type:0,
            mode:0
        }

        this.submitText=this.submitText.bind(this)
    }
    submitText(){
        console.log(this.state)

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
    render() {
        return(
            <div>
                <Nav/>
                <div id="text">
                    <input type="text" id="textTitle" maxLength="50" placeholder="请输入文章标题"/>

                    <div id="textContent">
                        <textarea placeholder="文章内容"/>
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
                    <button id="submitText" onClick={this.submitText}>发布文章</button>
                </div>

            </div>
        )
    }
}

export default WriteBlog