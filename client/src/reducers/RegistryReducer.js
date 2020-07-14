import { userLogin } from "../actions/RegistryActions";

import { USER_LOGIN } from "../actions/RegistryActions";

const initialState = { isLoggedin: false };
export const RegistryData = (state = initialState, action) => {
  switch (action.type) {
    case USER_LOGIN.SUCCESS:
      return { ...state, isLoggedin: true, userType: action.userType };
  }
  return state;
};
