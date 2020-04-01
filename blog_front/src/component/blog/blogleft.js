import React, {Component} from "react";

import './blogleft.css'


class BlogLeft extends Component{
    render() {
        return(
            <div>
                <div className="BlogLeftNav">
                    <ul>
                        <li>
                            <button onClick={this.props.BlogContentChoiceChange.bind(this,0)} >我的文章</button>
                        </li>
                        <li>
                            <button onClick={this.props.BlogContentChoiceChange.bind(this,1)}>我的收藏</button>
                        </li>
                        <li>
                            <button onClick={this.props.BlogContentChoiceChange.bind(this,2)}>我的专栏</button>
                        </li>
                    </ul>
                </div>

            </div>
        )
    }
}


export default BlogLeft