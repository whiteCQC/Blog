import React, {Component} from "react";
import "./search.css"

class Search extends Component{
    render(){
        return(
            <div className="search">
                    <input type="text" placeholder="请输入..."/>
                    <button><i>搜索</i></button>
            </div>
        )

    }
}

export default Search