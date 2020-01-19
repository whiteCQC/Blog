import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {Provider} from 'react-redux';
import store from './store/store';
import {IndexHome} from "./component/home/IndexHome";
import {BrowserRouter as Router, Route} from 'react-router-dom'
import Login from "./component/login/login";

ReactDOM.render(
    <Provider store={store}>
        <Router>
            <Route exact path='/' component={IndexHome} />
            <Route path='/login' component={Login}/>
        </Router>

    </Provider>
    , document.getElementById('root'));