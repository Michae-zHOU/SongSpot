import { TOGGLE_DRAWER } from "../actions/AppActions";
const initialState = { drawerList: [] };
export const AppData = (state = initialState, action) => {
  switch (action.type) {
    case TOGGLE_DRAWER:
      return {
        ...state,
        drawerList: toggleDrawerList(state.drawerList, action.drawerType),
      };
  }
  return state;
};

const toggleDrawerList = (drawerList, drawerType) => {
  const updator = drawerList.slice();
  let isFound = false;
  for (var i = 0; i < updator.length; i++) {
    if (updator[i] === drawerType) {
      updator.splice(i, 1);
      isFound = true;
    }
  }
  if (isFound === false) {
    updator.push(drawerType);
  }
  return updator;
};
