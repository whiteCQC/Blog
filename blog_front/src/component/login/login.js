import * as React from "react";
import {connect} from "react-redux";
import {UserLogin} from "../../redux/user/action";
import {Redirect} from "react-router-dom";
import "./login.css"

const Login = ({ dispatch }) => {
    let email,password
    return(
     localStorage.getItem("token")==null?
        <div className="login-form">
            <h1>用户登录</h1>
            <div className="login-head">
                <img src="../image/user.png" alt="用户"/>
            </div>
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


                <input className="inputForLogin" ref={node => email = node} placeholder="邮箱账号"/>
                <input className="inputForLogin" type="password" ref={node => password = node}
                       maxLength="18" placeholder="用户密码"/>

                <button type="submit" className="login-submit">登录</button>
                <div>
                    <p>没有账号？点击这里 <a href="/register" className="toRegister">注册</a></p>
                </div>

            </form>
            <div className="return">
                <a href='/' >返回</a>
            </div>

        </div>
        :
         <Redirect push to="/"/>
    )


}


export default connect()(Login)