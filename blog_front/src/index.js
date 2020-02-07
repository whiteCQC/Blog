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
import Profile from "./component/user/profile";
import Concern from "./component/user/concern";
import Fans from "./component/user/fan";

ReactDOM.render(
    <Provider store={store}>
        <Router>
            <Route exact path='/' component={IndexHome} />
            <Route exact path='/login' component={Login}/>
            <Route exact path='/register' component={Register} />
            <Route path='/article/:aid' component={ArticleBody}/>
            <Route path='/search/:keywords' component={SearchResult}/>

            <Route path='/person/profile' component={Profile}/>
            <Route path='/person/concern' component={Concern}/>
            <Route path='/person/fan' component={Fans}/>
        </Router>

    </Provider>
    , document.getElementById('root'));