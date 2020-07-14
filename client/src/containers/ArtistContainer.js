// import { bindActionCreators, Dispatch } from "redux";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";

import { Artist } from "../components/_Artist/Artist";
function mapStateToProps(state, ownProps) {
  return {
    id: state.ArtistData.id,
    songs: state.ArtistData.songs,
  };
}

export const ArtistContainer = connect(
  mapStateToProps
  // mapDispatchToProps
)(withRouter(Artist));
