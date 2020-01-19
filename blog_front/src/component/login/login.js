import React from "react";
import connect from "react-redux/lib/connect/connect";
import {UserLogin} from "../../redux/user/action";

class Login extends React.Component {
    construct(props) {
        this.state = {
            email: '',
            password: ''
        };
    }
    setUserInfo(event, key) {
        let obj = {};
        obj[key] = event.target.value;
        // 更新状态
        this.setState(obj);
    }
    render() {
        return(
            <div>
                <h3>用户登录</h3>
                <div>
                    <span>用户名：</span>
                    <span>
                        <input type="text" onInput={(event) => {
                            this.setUserInfo(event, 'email');
                        }}>
                        </input>
                    </span>
                </div>
                <div>
                    <span>密码：</span>
                    <span>
                        <input type="password" onInput={(event) => {
                            this.setUserInfo(event, 'password');
                        }}>
                        </input>
                    </span>
                </div>
                <div>

                    <button onClick={() => {
                        let userInfo = JSON.stringify({
                            email:this.state.email,
                            password:this.state.password
                        })
                        UserLogin(userInfo)

                    }}>登录</button>
                </div>
            </div>
        )
    }

}
export default connect(state => ({state: state}))(Login);