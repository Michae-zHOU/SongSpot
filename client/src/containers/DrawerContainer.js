// import { bindActionCreators, Dispatch } from "redux";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { CustomDrawer } from "../components/_Drawer/Drawer";

import { getIsLoggedin } from "../selectors/selectors";

function mapStateToProps(state, ownProps) {
  return {
    drawerList: state.AppData.drawerList,
  };
}

export const CustomDrawerManager = connect(
  mapStateToProps
  // mapDispatchToProps
)(CustomDrawer);
