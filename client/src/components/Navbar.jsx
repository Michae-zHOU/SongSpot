import React from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import Grid from "@material-ui/core/Grid";
import "./Navbar.scss";
import { theme } from "../colorPlatte";
import { ThemeProvider } from "@material-ui/core/styles";
import { Link } from "react-router-dom";
import Container from "@material-ui/core/Container";
import MenuIcon from "@material-ui/icons/Menu";
import AccountCircleIcon from "@material-ui/icons/AccountCircle";
export const Navbar = (props) => {
  const { isLoggedin, userType, toggleDrawer } = props;
  const navClass = `navbar ${isLoggedin ? "loggedIn" : ""}`;
  const handleBurgerClick = () => {
    switch (userType) {
      case "artist":
        toggleDrawer("artistNav", {});
    }
  };
  return (
    <Container className={navClass}>
      <ThemeProvider theme={theme}>
        <AppBar position="fixed" color="primary">
          <Toolbar>
            <Grid
              justify="space-between" // Add it here :)
              container
            >
              {!!isLoggedin && (
                <Grid item>
                  <Button color="inherit" onClick={handleBurgerClick}>
                    <MenuIcon />
                  </Button>
                </Grid>
              )}
              <Grid item>
                <Typography variant="title" color="inherit">
                  <Link to="/"> SONG SPOT</Link>
                </Typography>
              </Grid>
              {!!isLoggedin && (
                <Grid item>
                  <Button color="inherit">
                    <AccountCircleIcon />
                  </Button>
                </Grid>
              )}
              {!isLoggedin && (
                <Grid item>
                  <Button color="inherit">
                    <Link to="/curator">Curators</Link>
                  </Button>
                  <Button color="inherit">
                    <Link to="/artist">Artists</Link>
                  </Button>
                </Grid>
              )}
            </Grid>
          </Toolbar>
        </AppBar>
      </ThemeProvider>
    </Container>
  );
};
