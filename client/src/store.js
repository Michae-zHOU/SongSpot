import { AnyAction, applyMiddleware, createStore } from "redux";
import { appReducer } from "./reducers";

const rootReducer = (state, action) => {
  return appReducer(state, action);
};
export const store = createStore(rootReducer);

// store.subscribe(
//   throttle(() => {
//     saveApplicationState(mainData);
//   }, 1000)
// );
