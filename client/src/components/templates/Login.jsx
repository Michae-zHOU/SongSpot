import { ThemeProvider } from "@material-ui/core/styles";
import { Button, Box, TextField, Grid, Typography } from "@material-ui/core";
import { Link } from "react-router-dom";
import React, { Component } from "react";
import { theme } from "../../colorPlatte";
import "./Login.scss";
class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: ""
    };
  }
  render() {
    return (
      <div className="login">
        <ThemeProvider theme={theme}>
          <Box boxShadow={1} p={5} style={{ width: "30rem", height: "20rem" }}>
            <Grid justify="left" margin="110" className="login__title">
              <Typography variant="h5" align="left">
                Welcome
              </Typography>
            </Grid>
            <Grid item>
              <TextField
                hintText="Enter your Username"
                floatingLabelText="Username"
                id="standard-basic"
                label="Username"
                onChange={(event, newValue) =>
                  this.setState({ username: newValue })
                }
                mt="30px"
                fullWidth={true}
              />
            </Grid>
            <br />
            <Grid item>
              <TextField
                type="password"
                hintText="Enter your Password"
                floatingLabelText="Password"
                id="standard-basic"
                label="Password"
                onChange={(event, newValue) =>
                  this.setState({ password: newValue })
                }
                mt="30px"
                fullWidth={true}
              />
            </Grid>
            <br />

            <Button
              label="Submit"
              onClick={event => this.handleClick(event)}
              color="secondary"
            >
              Go
            </Button>
            <Link to="./register">Register</Link>
          </Box>
        </ThemeProvider>
      </div>
    );
  }
}
const style = {
  margin: 15
};
export default Login;
