import { createRequestTypes, action } from "./actionConfig";

export const USER_LOGIN = createRequestTypes("USER_LOGIN");
export const USER_LOGOUT = createRequestTypes("USER_LOGOUT");

const userLogin = (username, password) =>
  action(USER_LOGIN.REQUEST, { username, password });

const userLogout = () => action(USER_LOGOUT.REQUEST);

export const registryActions = { userLogin, userLogout };
