import Axios from "../../axios/axios";
import {openNotificationWithIcon} from '../../component/notification/index';

const viewArticle=(data)=>{
    return {type: "VIEW_ARTICLE", data: data};
}

function fetchArticle(data,dispatch){
    Axios.post('/article/detail', {
        aid:data
    }).then(({data}) => {
        if (data.code === 200) {
            dispatch(viewArticle(data.detail))
        } else {
            openNotificationWithIcon("error", "Error", data.description)
        }
    }).catch(error => {
        openNotificationWithIcon("error", "Error", error.message)
    })

}