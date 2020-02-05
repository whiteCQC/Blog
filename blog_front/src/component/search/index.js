/* 搜索结果页面 */

import React, {Component} from "react";
import {Nav} from "../header/header";

import './index.css'

class SearchResult extends Component{
    constructor (props) {
        super(props);

    }

    render() {
        return(
            <div>
                <Nav/>
            </div>
        )
    }
}

export default SearchResult