import * as React from "react";
import {connect} from "react-redux";
import {UserLogin} from "../../redux/user/action";
import {Redirect} from "react-router-dom";
import "./login.css"

const Login = ({ dispatch }) => {
    let email,password
    return(
     localStorage.getItem("token")==null?
        <div className="loginFrame">
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
                <h1>用户登录</h1>
                <div>
                    <span className="labelForLogin">用户名</span>
                    <span>
                        <input className="inputForLogin" ref={node => email = node}>
                        </input>
                    </span>
                </div>
                <div>
                    <span className="labelForLogin">密&nbsp;&nbsp;&nbsp;码</span>
                    <span>
                        <input className="inputForLogin" type="password" ref={node => password = node}>
                        </input>
                    </span>
                </div>
                <button type="submit">登录</button>
                <p>没有账号？点击这里</p>
                <a href="/register">注册</a>
            </form>
            <a href='/'>返回</a>
        </div>
        :
         <Redirect push to="/"/>
    )


}


export default connect()(Login)