import Axios from "../../axios/axios";
import {openNotificationWithIcon} from '../../component/notification/index';
//import {logger} from "redux-logger/src";

const Login = (data) =>({type: "LOGIN",data:data});


export const ToLogin = (data) => {
    //console.log(data);
    return dispatch => {
        UserLogin(data,dispatch);
    }
}

function UserLogin(data,dispatch) {
    console.log(data)
    const user = JSON.parse(data);
    console.log(user.userid)
    Axios.post("/login",{
            userid:user.userid,
            name:user.name
    })
        .then(({data}) => {
            console.log("here1:",data.detail)
            dispatch(Login(data.detail));
        }).catch(error => {
            {
                openNotificationWithIcon("error", "Error", error.message)
            }
        })

}