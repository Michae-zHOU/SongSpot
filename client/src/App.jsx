import React, { Suspense } from "react";
import { BrowserRouter, Redirect, Route, Switch } from "react-router-dom";
import { NavbarContainer } from "./containers/NavbarContainer";
import { HomePage } from "./components/templates/HomePage";
import "./App.scss";
import { Navbar } from "./components/Navbar";
import Grid from "@material-ui/core/Grid";
import Container from "@material-ui/core/Container";
import { LoginContainer } from "./containers/LoginContainer";
import CssBaseline from "@material-ui/core/CssBaseline";
import { ProtectedRouteContainer } from "./containers/ProtectedRouteContaienr";
import Register from "./components/templates/Register";
import { CustomDrawerManager } from "./containers/DrawerContainer";
import { ArtistContainer } from "./containers/ArtistContainer";

function App() {
  const isLogin = false;
  return (
    <BrowserRouter>
      <CssBaseline>
        <div className="App">
          <NavbarContainer />
          <div className="main">
            <Switch>
              <Route exact path="/">
                <HomePage />
              </Route>
              <ProtectedRouteContainer path="/artist">
                <ArtistContainer />
              </ProtectedRouteContainer>
              <ProtectedRouteContainer path="/curator">
                {/* <CuratorContainer /> */}
              </ProtectedRouteContainer>
              <Route exact path="/Login">
                <LoginContainer />
              </Route>
              <Route exact path="/Register">
                <Register />
              </Route>
            </Switch>
          </div>
        </div>
      </CssBaseline>
      <CustomDrawerManager />
    </BrowserRouter>
  );
}

export default App;
