import React, { Component } from 'react';
import {Route} from 'react-router-dom';
import {Layout,BackTop} from 'antd';

import Content from "./content";

//import Header from '../header/index';
//import Footer from '../footer/index';


const MyLayout = ({component:Component,...rest}) => {
    return(
        <Route {...rest} render={matchProps => (
            <Component {...matchProps}/>
        )}>
        </Route>
    );
};

export default MyLayout;