import React from "react";
import { Route, Redirect } from "react-router-dom";
const auth = {};
auth.isAuthenticated = false;
export const ProtectedRoute = ({ children, ...rest }) => {
  return (
    <Route
      {...rest}
      render={({ location }) =>
        auth.isAuthenticated ? (
          children
        ) : (
          <Redirect
            to={{
              pathname: "/login",
              state: { from: location }
            }}
          />
        )
      }
    />
  );
};
