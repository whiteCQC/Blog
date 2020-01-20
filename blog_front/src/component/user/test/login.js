import React, {Component} from "react";
import { connect } from 'react-redux'
import {ToLogin} from "../../../redux/test/action";

const LoginA = ({ dispatch }) => {
    let uid,uname

    return (
        <div>
            <form onSubmit={e => {
                e.preventDefault()
                if (!uid.value.trim()||!uname.value.trim()) {
                    return
                }
                let userInfo = JSON.stringify({
                    uid:uid.value,
                    uname:uname.value
                })
                dispatch(ToLogin(userInfo))
                uid.value = ''
                uname.value=''
            }}>
                <input ref={node => uid = node} />
                <input ref={node => uname = node} />
                <button type="submit">
                    Add
                </button>
            </form>
        </div>
    )

}
export default connect()(LoginA)