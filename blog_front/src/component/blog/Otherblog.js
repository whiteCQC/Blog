import React, {Component} from "react";
import SingleArticle from "./singleArticle";

class OtherBlog extends Component{
    constructor(props) {
        super(props);
        this.state = {
            articles: []
        }

    }
    static getDerivedStateFromProps(nextProps,preState){
        const oldData =JSON.stringify(preState)
        const newData =JSON.stringify(nextProps)
        if(oldData!==newData){
            let pub=[]
            nextProps.articles.forEach((a)=>{
                if(a.mode===0){
                    pub.push(a)
                }
            })
            return {
                articles:pub
            }
        }
        return null

    }
    render() {
        return(
            <div className="blogRight">
                <div className="buttonHeaders">
                    <h2>公开文章</h2>
                </div>
                <div className="blogArticles">
                    <ul>
                        {this.state.articles.map((article,index) =>
                            <li key={article.aid} >
                                <SingleArticle article={article} selectArticle={this.props.selectArticle} />
                            </li>
                        )}
                    </ul>
                </div>
            </div>
        )
    }
}

export default OtherBlog