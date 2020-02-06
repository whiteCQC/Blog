import * as React from "react";
import ToLogin from "../login/loginNav";
import "./header.css"
import Search from "../search/search";

export const Nav = () => {
    return (
        <div className="header">
            <a className="logo" href="/"><img src="../image/blog.png" alt="首页"/></a>
            <Search />
            <ToLogin/>
        </div>
    )
}
