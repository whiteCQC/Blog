import React, {Component} from "react";

export default class SingleArticle extends Component{

    render() {
        let article=this.props.article
        return(
            <div className="hotList" onClick={this.props.selectArticle.bind(this,article.aid)}>
                <h2>
                    {article.articleTitle}
                </h2>
                <p><span>{article.articleContent}</span></p>
                <div className="viewNum">
                    <img src="../image/eye.jpg" alt="浏览人数"/>{article.viewNum}
                </div>
            </div>
        )
    }
}

