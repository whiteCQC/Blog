import React, {Component} from "react";
import SingleArticle from "./singleArticle";
import { confirmAlert } from 'react-confirm-alert';
import 'react-confirm-alert/src/react-confirm-alert.css';

class MyBlog extends Component{
    constructor(props) {
        super(props);
        this.state={
            articlesAll:[],
            articles:[],
            articlesPub:[],
            articlesPri:[]
        }
        this.allArticle=this.allArticle.bind(this)
        this.publicArticle=this.publicArticle.bind(this)
        this.privateArticle=this.privateArticle.bind(this)
        this.edit=this.edit.bind(this)
        this.del=this.del.bind(this)
    }
    static getDerivedStateFromProps(nextProps,preState){
        const oldData =JSON.stringify(preState.articlesAll)
        const newData =JSON.stringify(nextProps.articles)
        if(oldData!==newData){
            let pub=[],pri=[]
            nextProps.articles.forEach((a)=>{
                if(a.mode===0){
                    pub.push(a)
                }else{
                    pri.push(a)
                }
            })

            return {
                articlesPub:pub,
                articlesPri:pri,
                articles:nextProps.articles,
                articlesAll:nextProps.articles
            }
        }
        return null
    }

    allArticle(){
        this.setState({
            articles:this.state.articlesAll
        })
    }
    publicArticle(){
        this.setState({
            articles:this.state.articlesPub
        })
    }
    privateArticle(){
        this.setState({
            articles:this.state.articlesPri
        })
    }
    edit(aid){
        this.props.history.push("/write", {
            aid: aid
        });
    }
    del= (aid) => {
        confirmAlert({
            title: '提交确认',
            message: '真的要删除此文章吗？',
            buttons: [
                {
                    label: '确认',
                    onClick: () => {
                        this.props.ArticleDel(aid)
                    }
                },
                {
                    label: '放弃',
                    onClick: () => {}
                }
            ]
        });
    };
    render() {
        return(
            <div className="blogRight">
                <div className="buttonHeaders">
                    <button id="_all" onClick={this.allArticle}>全部</button>
                    <button id="_pub" onClick={this.publicArticle}>公开</button>
                    <button id="_pri" onClick={this.privateArticle}>私密</button>
                </div>
                <div className="blogArticles">
                    <ul>
                        {this.state.articles.map((article,index) =>
                            <li key={article.aid}>
                                <SingleArticle article={article} selectArticle={this.props.selectArticle}/>
                                <div className="change-article">
                                    <button onClick={this.edit.bind(this,article.aid)}>编辑</button>
                                    <button onClick={this.del.bind(this,article.aid)}>删除</button>
                                </div>
                            </li>
                        )}
                    </ul>
                </div>

            </div>
        )
    }
}

export default MyBlog