import {connect} from "react-redux";
import React from "react";
import Login from "./login"

const Todo = ( {text}) => (
    <li>
        {text}
    </li>
)

const TodoList = ({users}) => (
    <ul>
        {
            users.map((user,index) =>
                <li key={index}>{user.user.name}</li>
            )
        }
    </ul>
)

const mapStateToProps = state => ({
    users: state.user
})

export default connect(mapStateToProps)(TodoList)