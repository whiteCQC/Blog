const interaction = (state = [], action) => {
    switch (action.type) {
        case 'FOLLOW':
            return {
                ...state
            }
        case 'CANCEL_FOLLOW':
            return {
                ...state
            }
        default:
            return state
    }
}

export default interaction