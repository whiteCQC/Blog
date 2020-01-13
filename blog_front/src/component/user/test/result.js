import {connect} from "react-redux";
import React from "react";


const TodoList = ({users}) => (
    <ul>
        {
            users.map((user,index) =>
                <li key={index}>{user.user.uname}</li>
            )
        }
    </ul>
)

const mapStateToProps = state => ({
    users: state.user
})

export default connect(mapStateToProps)(TodoList)