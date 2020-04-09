import React, {Component} from "react";
import ViewNum from "../../image/eye.jpg"
import {dateTransfer} from "../../tools/tansfer";
export default class SingleArticle extends Component{

    render() {
        let article=this.props.article
        return(
            <div className="hotList" onClick={this.props.selectArticle.bind(this,article.aid)}>
                <h2>
                    {article.articleTitle}
                </h2>
                <p><span>{dateTransfer(article.date)}</span></p>
                <div className="viewNum">
                    <img src={ViewNum} alt="浏览人数"/>{article.viewNum}
                </div>
            </div>
        )
    }
}

