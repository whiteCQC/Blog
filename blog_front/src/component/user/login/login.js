import React, {Component} from "react";
import { connect } from 'react-redux'
import {ToLogin} from "../../../redux/index/action";
import qs from 'qs'

const Login = ({ dispatch }) => {
    let userid,name

    return (
        <div>
            <form onSubmit={e => {
                e.preventDefault()
                if (!userid.value.trim()||!name.value.trim()) {
                    return
                }
                let userInfo = JSON.stringify({
                    userid:userid.value,
                    name:name.value
                })
                dispatch(ToLogin(userInfo))
                userid.value = ''
                name.value=''
            }}>
                <input ref={node => userid = node} />
                <input ref={node => name = node} />
                <button type="submit">
                    Add
                </button>
            </form>
        </div>
    )

}
export default connect()(Login)