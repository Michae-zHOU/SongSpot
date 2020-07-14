import { AnyAction, applyMiddleware, createStore } from "redux";
import { appReducer } from "./reducers";
import createSagaMiddleware from "redux-saga";
import { composeWithDevTools } from "redux-devtools-extension";
import rootSaga from "./sagas/sagas";
const sagaMonitor = require("@redux-saga/simple-saga-monitor");
const rootReducer = (state, action) => {
  return appReducer(state, action);
};

export const configureStore = () => {
  const sagaMiddleware = createSagaMiddleware({ sagaMonitor });
  const store = createStore(
    rootReducer,
    composeWithDevTools(applyMiddleware(sagaMiddleware))
  );

  sagaMiddleware.run(rootSaga);
  return store;
};

// store.subscribe(
//   throttle(() => {
//     saveApplicationState(mainData);
//   }, 1000)
// );
