import React, {Component} from "react";
import {withRouter} from "react-router-dom";

import './blogleft.css'


class BlogLeft extends Component{
    constructor(props) {
        super(props);
        this.state = {
            flag:false,//是否为当前登录用户
        }
    }
    handleCollect= (e)=>{

    }
    handleArticle(){

    }
    handleCol= (e)=>{

    }

    render() {
        return(
            <div>
                <div className="BlogLeftNav">
                    <ul>
                        <li>
                            <button onClick={this.handleArticle}>我的文章</button>
                        </li>
                        <li>
                            <button onClick={this.handleCollect}>我的收藏</button>
                        </li>
                        <li>
                            <button onClick={this.handleCol}>我的专栏</button>
                        </li>
                    </ul>
                </div>

            </div>
        )
    }
}


export default withRouter(BlogLeft)