export function user(state = {
    user: null,
    loginUid: localStorage.getItem("uid"),
},action) {
    switch (action.type) {
        case "LOGIN":
            return {
                ...state,
                loginUid: action.data.username,
            }
        case "REGISTER":
            return {
                ...state,
                loginUid: action.data.username,
            }
        case "LOGOUT":
            return {
                ...state,
                loginUid: null
            }
        default:
            return state
    }
}