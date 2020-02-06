/* 搜索结果页面 */

import React, {Component} from "react";
import {Nav} from "../header/header";
import {connect} from "react-redux";

import './index.css'

class SearchResult extends Component{
    constructor (props) {
        super(props);

    }
    componentDidMount (){
        let keywords=this.props.location.state.keywords
        let pageNum = this.props.location.state.pageNum
        ///console.log(keywords+","+pageNum)

    }

    render() {
        return(
            <div>
                <Nav/>
            </div>
        )
    }
}

export default connect()(SearchResult)