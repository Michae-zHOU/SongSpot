// import { bindActionCreators, Dispatch } from "redux";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import ProtectedRoute from "../components/ProtectedRoute";
import { getIsLoggedin } from "../selectors/selectors";

function mapStateToProps(state, ownProps) {
  return {
    isLoggedin: getIsLoggedin(state),
    path: ownProps.path,
  };
}

export const ProtectedRouteContainer = connect(
  mapStateToProps
  // mapDispatchToProps
)(withRouter(ProtectedRoute));
