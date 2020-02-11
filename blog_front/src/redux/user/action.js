import Axios from "../../axios/axios";

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
    localStorage.removeItem("token")
    localStorage.removeItem("uid")
    localStorage.removeItem("uname")
    return {type: "LOGOUT"}
}

export const UserLogin = (data) => {
    return dispatch => {
        fetchLogin(data,dispatch);
    }
}

export const UserRegister = (data) =>{
    return dispatch => {
        fetchRegister(data,dispatch);
    }
}

// 登录
function fetchLogin (data,dispatch){
    const user = JSON.parse(data)
    Axios.post('/login', {
        email: user.email,
        password: user.password
    }).then(({data}) => {
        if (data.code === 200) {
            localStorage.setItem("token", data.detail.token);
            localStorage.setItem("uid", data.detail.uid);
            localStorage.setItem("uname", data.detail.uname);
            Axios.defaults.headers.common['Authorization'] = data.detail.token
            dispatch(login(data.detail))

            window.location.reload()
        } else {
            alert(data.description)
        }
    }).catch(error => {
        alert(error.message)
    })

}

export function fetchRegister (data,dispatch){
    const user = JSON.parse(data)
    Axios.post('/register', {
        email: user.email,
        uname:user.uname,
        password: user.password
    }).then(({data}) => {
        if (data.code === 200) {
            localStorage.setItem("token", data.detail.token);
            localStorage.setItem("uid", data.detail.uid);
            localStorage.setItem("uname", data.detail.uname);
            Axios.defaults.headers.common['Authorization'] = data.detail.token
            dispatch(register(data.detail))

            window.location.reload()
        } else {
            alert(data.description)
        }
    }).catch(error => {
        alert(error.message)
    })

}


