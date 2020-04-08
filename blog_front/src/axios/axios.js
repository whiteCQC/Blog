import axios from 'axios';
//import qs from 'qs';

const Axios = axios.create({
    baseURL: 'http://172.19.240.137:8081' //设置访问路径
});
Axios.defaults.headers.common['Authorization'] = localStorage.getItem("token");
Axios.defaults.headers['Content-Type'] = 'application/json; charset=UTF-8';

Axios.interceptors.response.use(
    res => {
        // 如果返回的code是202，则表示token有问题，直接把登录信息清除
        if (res.data && res.data.code === 202) {
            localStorage.removeItem("username");
            localStorage.removeItem("Authorization");
            window.location.reload();
        } else if (res.data && res.data.code === 500) {
            res.data.description = "服务器异常";
        }
        return res;
    }
);

export default Axios