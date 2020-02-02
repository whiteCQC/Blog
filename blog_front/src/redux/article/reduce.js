const todos = (state = [], action) => {
    switch (action.type) {
        case 'VIEW_ARTICLE':
            return {
                ...state,
                articleID:action.data.aid
            }
        case '':
        default:
            return state
    }
}

export default todos