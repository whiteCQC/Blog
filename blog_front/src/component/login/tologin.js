import React from "react";
import connect from "react-redux/lib/connect/connect";
import {UserLogin} from "../../redux/user/action";

const ToLogin = () => {

    return localStorage.getItem("token")==null?
        <div>
            <a href="/login">登录/注册</a>
        </div>
        :

        <div>
            欢迎您:{localStorage.getItem("uname")}
        </div>



}



export default ToLogin