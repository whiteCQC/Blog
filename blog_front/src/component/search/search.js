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
        let keywords = cutstr(this.state.searchText,30)
        let res= "/search/" + keywords
        this.props.history.push(res)
        window.location.reload()
    }
    handleInput= (e)=>{
        this.setState({
            searchText:e.target.value
        })
    }

    render(){
        return(
            <div className="search">
                <input onChange={this.handleInput} type="text" placeholder="请输入..."/>
                <button onClick={this.handleSearch}><i>搜索</i></button>
            </div>
        )

    }
}

export default withRouter(Search)