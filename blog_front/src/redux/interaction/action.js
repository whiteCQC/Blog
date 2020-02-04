import Axios from "../../axios/axios";
import {openNotificationWithIcon} from '../../component/notification/index';

const follow = (data)=>{
    return {type: "FOLLOW", data: data};
}

export const followExecute = (data)=>{
    return dispatch => {
        followFetch(data,dispatch)
    }
}
function followFetch(data,dispatch){
    const users = JSON.parse(data)
    console.log(users)
    Axios.post('/FanAddTest',{
        followId:users.followId,
        followedId:users.followedId
    }).then(({data}) => {
        if (data.code === 200) {
            dispatch(follow(data))
            alert("成功关注")
        }else{
            alert("请勿重复关注")
        }
    }).catch(
        error => { openNotificationWithIcon("error", "Error", error.message)
    })
}