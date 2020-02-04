import {combineReducers} from 'redux';
import {user} from "./user/reduce";
import interaction from "./interaction/reduce";

export default combineReducers({
    user,
    interaction
})