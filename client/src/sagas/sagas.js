import { put, takeLatest, all } from "redux-saga/effects";
import { USER_LOGIN, USER_LOGOUT } from "../actions/RegistryActions";

function* userLogin() {
  try {
    yield put({ type: USER_LOGIN.SUCCESS, userType: "artist" });
  } catch (error) {
    console.log(error);
  }
}

// function* userLogout(){
//     try {
//         yield put({type:USER_LOGOUT.SUCCESS})
//     }
//     catch (error){(console.log(err)}
// }

function* actionWatcher() {
  yield takeLatest(USER_LOGIN.REQUEST, userLogin);
}

export default function* rootSaga() {
  yield all([actionWatcher()]);
}
