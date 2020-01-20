import * as React from "react";
import {connect} from "react-redux";
import {logout} from "../../redux/user/action";

const ToLogin = ({dispatch}) => {
    function handleLogout(e) {
        e.preventDefault();
        dispatch(logout());
        window.location.reload()
    }
    return localStorage.getItem("token")==null?
        <div className="logNav">
            <a href="/login">登录/注册</a>
        </div>
        :

        <div className="logNav">
            欢迎您:{localStorage.getItem("uname")}
            <a href="#" onClick={handleLogout}>
                登出
            </a>
        </div>



}



export default connect()(ToLogin)