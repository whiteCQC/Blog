import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {Provider} from 'react-redux';
import store from './store/store';
import IndexHome from "./component/home/body";
import Login from "./component/user/login/login";
import Todo from "./component/user/login/result";

ReactDOM.render(
    <Provider store={store}>
        <IndexHome />
        <Login />
        <Todo />
    </Provider>
    , document.getElementById('root'));