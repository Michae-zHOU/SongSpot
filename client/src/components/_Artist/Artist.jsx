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
export const Navbar = props => {
  return (
    <Container className="navbar">
      <ThemeProvider theme={theme}>
        <AppBar position="fixed" color="primary">
          <Toolbar>
            <Grid
              justify="space-between" // Add it here :)
              container
            >
              <Grid item>
                <Typography variant="title" color="inherit">
                  <Link to="/"> SONG SPOT</Link>
                </Typography>
              </Grid>
              <Grid item>
                <Button color="inherit">
                  <Link to="/curator">Curators</Link>
                </Button>
                <Button color="inherit">
                  <Link to="/artist">Artists</Link>
                </Button>
              </Grid>
            </Grid>
          </Toolbar>
        </AppBar>
      </ThemeProvider>
    </Container>
  );
};
