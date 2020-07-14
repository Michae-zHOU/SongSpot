import React, { useState, useEffect } from "react";
import { Route, Redirect, withRouter } from "react-router-dom";

const ProtectedRoute = (props) => {
  const { children, isLoggedin, ...res } = props;

  return (
    <Route
      {...res}
      render={({ location }) =>
        isLoggedin ? (
          children
        ) : (
          <Redirect
            to={{
              pathname: "/login",
              state: { from: location },
            }}
          />
        )
      }
    />
  );
};

export default withRouter(ProtectedRoute);
