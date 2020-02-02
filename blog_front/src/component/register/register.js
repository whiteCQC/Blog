import {Redirect} from "react-router-dom";
import * as React from "react";
import {connect} from "react-redux";
import {UserRegister} from "../../redux/user/action";
import {openNotificationWithIcon} from "../notification";

const Register= ({ dispatch }) => {
    let email,pw1,pw2,name
    return(
        localStorage.getItem("token")==null?
            <div>
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
                    <h3>用户登录</h3>
                    <div>
                        <span>邮箱：</span>
                        <span>
                        <input ref={node => email = node}>
                        </input>
                    </span>
                    </div>
                    <div>
                        <span>用户名：</span>
                        <span>
                        <input ref={node => name = node}>
                        </input>
                    </span>
                    </div>
                    <div>
                        <span>密码：</span>
                        <span>
                        <input type="password" ref={node => pw1 = node}>
                        </input>
                    </span>
                    </div>
                    <div>
                        <span>密码确认：</span>
                        <span>
                        <input type="password" ref={node => pw2 = node}>
                        </input>
                    </span>
                    </div>
                    <button type="submit">注册</button>
                </form>
                <a href='/'>返回</a>
            </div>
            :
            <Redirect push to="/"/>
    )
}

export default connect()(Register)