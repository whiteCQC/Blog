import React, { Component } from 'react';
import Axios from "../../axios/axios";
import {connect} from "react-redux";

import './left.css'

class UserLeftNav extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return(
            <div className="UserLeftNav">
                <ul>
                    <li>
                        <a href="/profile">个人资料</a>
                    </li>
                    <li>
                        <a href="/personal/store">我的收藏</a>
                    </li>
                    <li>
                        <a>我的关注</a>
                    </li>
                    <li>
                        <a>我的粉丝</a>
                    </li>
                    <li>
                        <a>我的博客</a>
                    </li>
                </ul>
            </div>
        )
    }
}

export default connect()(UserLeftNav)