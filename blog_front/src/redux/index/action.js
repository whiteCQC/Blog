import Axios from "../../axios/axios";
import {openNotificationWithIcon} from '../../component/notification/index';
import qs from 'qs'

const Login = (data) =>({type: "LOGIN",data:data});


export const ToLogin = (data) => {
    //console.log(data);
    return dispatch => {
        UserLogin(data,dispatch);
    }
}

function UserLogin(data,dispatch) {
    const user= JSON.parse(data)
    //let user = qs.stringify()
    console.log(user)
    Axios.get('/loginTest',
        {params:user
        }
    )
        .then(({data}) => {
            console.log("here1:",data.detail)
            dispatch(Login(data.detail));
        }).catch(error => {
            {
                openNotificationWithIcon("error", "Error", error.message)
            }
        })

}