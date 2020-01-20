import React, { Component } from 'react';
import Axios from "../../axios/axios";
import {openNotificationWithIcon} from "../notification";

import './hots.css';

class Hots extends Component{
    constructor (props) {
        super(props);
        this.state = {
            articles:[]
        }
        this.selectHot = this.selectHot.bind(this);
    }
    componentDidMount (){
        Axios.get("/article/hotTest").then(({data}) => {
            if(data.code === 200){
                this.setState({
                    articles: data.detail
                });
            }else{
                openNotificationWithIcon("error","Error",data.description)
            }
        }).catch( error => {
            openNotificationWithIcon("error","Error",error.message)
        })
    }
    selectHot(articleId){

    }
    render(){
        return(
            <div id="hotArticle">
                <ul>
                    {this.state.articles.map((article,index) =>
                        <SingleHot article={article} key={article.aid} onClick={this.selectHot(article.aid)}/>
                    )}
                </ul>
            </div>
        )
    }
}

const SingleHot = ({article},index)=>(
    <li>
        <div className="hotList">
            <h2>
                <span className="hotLogo">HOT</span>
                {article.articleTitle}
            </h2>
            <p><span>{article.articleContent}</span></p>
            <div className="viewNum">
                <img src="image/eye.jpg" alt="浏览人数"/>{article.viewNum}
            </div>
        </div>


    </li>
)

export default Hots;