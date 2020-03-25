import React,{Component} from "react";
import './RightUser.css'

class RightUser extends Component{
    render() {
        return(
            <div className="RightUser">
                <div>
                    <img className="avatar" src="../image/avatar.jpg" alt='头像'/>
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