import { bindActionCreators, Dispatch } from "redux";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { Navbar } from "../components/Navbar";
import { getIsLoggedin } from "../selectors/selectors";
import { toggleDrawer } from "../actions/AppActions";
function mapStateToProps(state) {
  return {
    isLoggedin: getIsLoggedin(state),
    userType: state.RegistryData.userType,
  };
}

function mapDispatchToProps(dispatch) {
  const combinedActions = {
    // ...AssetsActions,
    toggleDrawer,
  };
  return bindActionCreators(combinedActions, dispatch);
}

export const NavbarContainer = connect(
  mapStateToProps,
  mapDispatchToProps
)(withRouter(Navbar));
