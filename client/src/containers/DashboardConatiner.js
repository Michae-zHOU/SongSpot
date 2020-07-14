// import { bindActionCreators, Dispatch } from "redux";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";

function mapStateToProps(state, ownProps) {
  return {};
}

export const DashboardContainer = connect(
  mapStateToProps
  // mapDispatchToProps
)(withRouter(Dashboard));
