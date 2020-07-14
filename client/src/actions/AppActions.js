import { createRequestTypes, action } from "./actionConfig";
export const TOGGLE_DRAWER = "TOGGLE_DRAWER";
export const toggleDrawer = (drawerType, options) => {
  return action(TOGGLE_DRAWER, { drawerType, options });
};
