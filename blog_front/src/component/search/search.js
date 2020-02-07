import React, {Component} from "react";
import "./search.css"
import {withRouter} from "react-router-dom";

import {cutstr} from "../../tools/tansfer"

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
        let res= "/search/" + keywords
        this.props.history.push(res)
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

export default withRouter(Search)