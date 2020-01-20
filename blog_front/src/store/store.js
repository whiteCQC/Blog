import {createStore,applyMiddleware} from 'redux';
import {createLogger} from 'redux-logger';
import thunk from 'redux-thunk';

import reduce from '../redux/combineReducers';

const middleware = applyMiddleware(thunk,createLogger());
const store =createStore(reduce,middleware);
export default store;