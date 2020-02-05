import * as React from "react";
import {connect} from "react-redux";
import {logout} from "../../redux/user/action";

import './loginNav.css'

const ToLogin = ({dispatch}) => {
    function handleLogout(e) {
        dispatch(logout());
        window.location.reload()
    }
    function handleProfile(e){

        window.location.reload()
    }
    return localStorage.getItem("token")==null?
        <div className="logNav">
            <a href="/login">登录/注册</a>
        </div>
        :

        <div className="logNav">
            <span>欢迎您:{localStorage.getItem("uname")}</span>
            <a href="/personProfile" onClick={handleProfile}>
                个人中心
            </a>
            <a href="/" onClick={handleLogout}>
                登出
            </a>
        </div>



}



export default connect()(ToLogin)