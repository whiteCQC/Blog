import React, {Component} from "react";
import "./search.css"
import {withRouter} from "react-router-dom";

class Search extends Component{
    constructor (props) {
        super(props);
        this.state = {
            searchText:""
        }
        this.handleSearch=this.handleSearch.bind(this)
    }
    handleSearch(){
        let keywords = cutstr(this.state.searchText.value,30)
        this.props.history.push({pathname:"/search",state:{keywords:keywords,pageNum:1}})
    }

    render(){
        return(
            <div className="search">
                <input ref={node=>this.state.searchText=node} type="text" placeholder="请输入..."/>
                <button onClick={this.handleSearch}><i>搜索</i></button>
            </div>
        )

    }
}
function cutstr(str, len) {
    var str_length = 0;
    var str_len = 0;
    let str_cut;
    str_cut = new String();
    str_len = str.length;
    for (var i = 0; i < str_len; i++) {
        let a;
        a = str.charAt(i);
        str_length++;
        if (escape(a).length > 4) {
            //中文字符的长度经编码之后大于4
            str_length++;
        }
        str_cut = str_cut.concat(a);
        if (str_length >= len) {
            str_cut = str_cut.concat("");
            return str_cut;
        }
    }
    //如果给定字符串小于指定长度，则返回源字符串；
    if (str_length < len) {
        return str;
    }
}
export default withRouter(Search)