import Axios from "../../axios/axios";
import {openNotificationWithIcon} from '../../component/notification/index';

// 注册
const register = (data) => {
    return {type: "REGISTER", data: data};
}

// 登录
const login = (data) => {
    return {type: "LOGIN", data: data}
}

// 登出
export const logout = () => {
    return {type: "LOGOUT"}
}


// 登录
export function UserLogin (data){
    const user = JSON.parse(data)
    return dispatch => {
        console.log(user.email)
        Axios.post('/loginTest', {
            email: user.email,
            password: user.password
        }).then(({res}) => {
            if (res.code === 200) {
                localStorage.setItem("token", res.detail.token);
                localStorage.setItem("uid", res.detail.uid);
                localStorage.setItem("uname", res.detail.uname);
                Axios.defaults.headers.common['Authorization'] = res.detail.token
                dispatch(login(res.detail))
            } else {
                openNotificationWithIcon("error", "Error", res.description)
            }
        }).catch(error => {
            openNotificationWithIcon("error", "Error", error.message)
        })
    }
}
