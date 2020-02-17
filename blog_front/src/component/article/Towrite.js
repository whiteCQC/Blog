import React, { Component } from 'react';
import {withRouter} from "react-router-dom";

import './Towrite.css'
class ToWrite extends Component{
    constructor (props) {
        super(props);
        this.ToWrite=this.ToWrite.bind(this)
    }
    ToWrite(){
        if(localStorage.getItem("token")==null){
            this.props.history.push("/login")
        }else{
            this.props.history.push("/write")
        }
    }
    render() {
        return(
            <div className="toWrite">
                <button onClick={this.ToWrite}>写文章</button>
            </div>
        )

    }
}

export default withRouter(ToWrite)