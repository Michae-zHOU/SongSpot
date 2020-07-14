// import { bindActionCreators, Dispatch } from "redux";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import Login from "../components/templates/Login";
import ProtectedRoute from "../components/ProtectedRoute";
import { getIsLoggedin } from "../selectors/selectors";

function mapStateToProps(state, ownProps) {
  return {
    isLoggedin: getIsLoggedin(state),
  };
}

export const LoginContainer = connect(
  mapStateToProps
  // mapDispatchToProps
)(withRouter(Login));
