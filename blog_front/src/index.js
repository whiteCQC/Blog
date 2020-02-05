import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {Provider} from 'react-redux';
import store from './store/store';
import {BrowserRouter as Router, Route} from 'react-router-dom'

import Register from "./component/register/register";
import Login from "./component/login/login";
import IndexHome from "./component/home/IndexHome";
import ArticleBody from "./component/article/article";
import SearchResult from "./component/search/index";


ReactDOM.render(
    <Provider store={store}>
        <Router>
            <Route exact path='/' component={IndexHome} />
            <Route exact path='/login' component={Login}/>
            <Route exact path='/register' component={Register} />
            <Route path='/article' component={ArticleBody}/>
            <Route path='/search' component={SearchResult}/>
        </Router>

    </Provider>
    , document.getElementById('root'));