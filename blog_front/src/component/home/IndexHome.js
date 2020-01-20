import Hots from "../article/hots";
import React, {Component} from "react";
import {Nav} from "../header/header";

class IndexHome extends Component{
    render() {
        return(
            <div>
                <Nav/>
                <Hots/>
            </div>
        )
    }
}
export default IndexHome;

