import * as React from "react";
import ToLogin from "../login/loginNav";
import "./header.css"
import Search from "../search/search";
import ToWrite from "../article/Towrite";

import Logo from "../../image/blog.png"

export const Nav = () => {
    return (
        <div>
            <div className="header">
                <a className="logo" href="/"><img src={Logo} alt="首页"/></a>
                <Search />
                <ToWrite/>
                <ToLogin/>
            </div>
            <hr/>
        </div>
    )
}
