import React,{Component} from "react";
import './RightUser.css'

class RightUser extends Component{
    constructor(props) {
        super(props);
    }
    render() {
        return(
            <div className="RightUser">
                <div>
                    <img src="../image/avatar0.jpg"/>
                </div>
                <div className="userName">
                    <span >{this.props.user.uname}</span>
                </div>
                <br/>
                <div>
                    邮箱：{this.props.user.email}
                </div>
            </div>
        )
    }
}

export default RightUser