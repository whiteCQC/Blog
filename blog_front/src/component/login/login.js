import * as React from "react";
import {connect} from "react-redux";
import {UserLogin} from "../../redux/user/action";
import {Redirect} from "react-router-dom";

const Login = ({ dispatch }) => {
    let email,password
    return(
     localStorage.getItem("token")==null?
        <div>
            <form onSubmit={e => {
                e.preventDefault()
                if (!email.value.trim()||!password.value.trim()) {
                    return
                }
                let userInfo = JSON.stringify({
                    email:email.value,
                    password:password.value
                })
                dispatch(UserLogin(userInfo))


            }}>
                <h3>用户登录</h3>
                <div>
                    <span>用户名：</span>
                    <span>
                        <input ref={node => email = node}>
                        </input>
                    </span>
                </div>
                <div>
                    <span>密码：</span>
                    <span>
                        <input type="password" ref={node => password = node}>
                        </input>
                    </span>
                </div>
                <button type="submit">登录</button>
            </form>
        </div>
        :
         <Redirect push to="/"/>
    )


}


export default connect()(Login)