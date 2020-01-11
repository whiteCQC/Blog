const user = (state = [], action) => {
    switch (action.type) {
        case 'LOGIN':
            return [
                ...state,
                {
                    user:action.data
                }
            ]
        default:
            return state
    }
}

export default user