import React, { Suspense } from "react";
import { BrowserRouter, Redirect, Route, Switch } from "react-router-dom";
import { NavbarContainer } from "./containers/NavbarContainer";
import { HomePage } from "./components/templates/HomePage";
import "./App.scss";
import { Navbar } from "./components/Navbar";
import Grid from "@material-ui/core/Grid";
import Container from "@material-ui/core/Container";
import Login from "./components/templates/Login";
import CssBaseline from "@material-ui/core/CssBaseline";
import { ProtectedRoute } from "./ProtectedRoute";
import Register from "./components/templates/Register";
import { CustomDrawer } from "./components/_Drawer/Drawer";
function App() {
  const isLogin = false;
  return (
    <BrowserRouter>
      <CssBaseline>
        <div className="App">
          <NavbarContainer />
          <div className="main">
            <Route exact path="/">
              <HomePage />
            </Route>
            <ProtectedRoute path="/Artist">
              {/* <ArtistContainer /> */}
            </ProtectedRoute>
            <ProtectedRoute path="/Curator">
              {/* <CuratorContainer /> */}
            </ProtectedRoute>
            <Route exact path="/Login">
              <Login />
            </Route>
            <Route exact path="/Register">
              <Register />
            </Route>
          </div>
        </div>
      </CssBaseline>
      {/* <CustomDrawer /> */}
    </BrowserRouter>
  );
}

export default App;
