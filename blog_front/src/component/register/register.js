import {Redirect} from "react-router-dom";
import * as React from "react";
import {connect} from "react-redux";
import {UserRegister} from "../../redux/user/action";
import {openNotificationWithIcon} from "../notification";

import './register.css'

const Register= ({ dispatch }) => {
    let email,pw1,pw2,name
    return(
        localStorage.getItem("token")==null?
            <div className="register-form">
                <h1>用户注册</h1>
                <div className="register-head">
                    <img src="../image/avatar.jpg" alt='用户' height="150" width="150"/>
                </div>
                <form onSubmit={e => {
                    e.preventDefault()
                    if (!email.value.trim()||!pw1.value.trim()||!name.value.trim()) {
                        openNotificationWithIcon("error", "不能为空", "")
                        return
                    }
                    if(pw1.value.trim()!==pw2.value.trim()){
                        openNotificationWithIcon("error", "密码不一致", "")
                        return
                    }
                    let userInfo = JSON.stringify({
                        email:email.value,
                        password:pw1.value,
                        uname:name.value
                    })
                    dispatch(UserRegister(userInfo))
                }}>
                    <input className="inputForRegister" ref={node => email = node} placeholder="邮箱账号"/>
                    <input className="inputForRegister" ref={node => name = node} maxLength="20" placeholder="用户昵称"/>
                    <input className="inputForRegister" type="password" ref={node => pw1 = node} maxLength="18" placeholder="用户密码"/>
                    <input className="inputForRegister" type="password" ref={node => pw2 = node} maxLength="18" placeholder="密码确认"/>

                    <div>
                        <button type="submit" className="register-submit">注册</button>
                    </div>
                </form>
                <a href='/'>返回</a>
            </div>
            :
            <Redirect push to="/"/>
    )
}

export default connect()(Register)