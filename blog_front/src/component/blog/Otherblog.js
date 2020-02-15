import React, {Component} from "react";
import SingleArticle from "./singleArticle";

class OtherBlog extends Component{
    constructor(props) {
        super(props);
        this.state = {
            articles: []
        }

    }
    componentWillReceiveProps(nextProps){
        let pub=[]
        nextProps.articles.map((a)=>{
            if(a.mode===0){
                pub.push(a)
            }
        })
        this.setState({
            articles:pub
        })
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