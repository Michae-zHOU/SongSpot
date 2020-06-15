// import { bindActionCreators, Dispatch } from "redux";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { Navbar } from "../components/Navbar";

function mapStateToProps(state) {
  return {};
}

export const NavbarContainer = connect(
  mapStateToProps
  //   mapDispatchToProps
)(withRouter(Navbar));
