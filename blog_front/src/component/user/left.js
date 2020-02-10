import React, { Component } from 'react';
import {connect} from "react-redux";
import {withRouter} from "react-router-dom";

import './left.css'

class UserLeftNav extends Component {
    constructor(props) {
        super(props);
        this.state=({
            uid:localStorage.getItem("uid")
        })
        this.myBlog=this.myBlog.bind(this)
        this.myProfile=this.myProfile.bind(this)
        this.myConcern=this.myConcern.bind(this)
        this.myFan=this.myFan.bind(this)
    }
    myBlog(){
        let res="/blog/" + this.state.uid;
        this.props.history.push(res)
    }
    myProfile(){
        this.props.history.push("/person/profile")
    }
    myConcern(){
        this.props.history.push("/person/concern")
    }
    myFan(){
        this.props.history.push("/person/fan")
    }
    render() {
        return(
            <div className="UserLeftNav">
                <ul>
                    <li>
                        <button onClick={this.myProfile}>个人资料</button>
                    </li>
                    <li>
                        <button onClick={this.myConcern}>我的关注</button>
                    </li>
                    <li>
                        <button onClick={this.myFan}>我的粉丝</button>
                    </li>
                    <li>
                        <button onClick={this.myBlog}>我的博客</button>
                    </li>
                </ul>
            </div>
        )
    }
}

export default connect()(withRouter(UserLeftNav))