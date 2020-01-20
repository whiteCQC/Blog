import * as React from "react";
import ToLogin from "../login/loginNav";
import "./header.css"
import Search from "../search/search";

export const Nav = () => {
    return (
        <div className="header">
            <Search />
            <ToLogin/>
        </div>
    )
}
