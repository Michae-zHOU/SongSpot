import { ThemeProvider } from "@material-ui/core/styles";
import { Button, Box, TextField, Grid, Typography } from "@material-ui/core";
import { Link, withRouter, useHistory, useLocation } from "react-router-dom";
import React, { useState } from "react";
import { theme } from "../../colorPlatte";
import { useDispatch } from "react-redux";
import { registryActions } from "../../actions/RegistryActions";
import "./Login.scss";
import { useEffect } from "react";

const Login = (props) => {
  const { isLoggedin } = props;
  let history = useHistory();
  let location = useLocation();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();

  const handleSubmit = (evt) => {
    //avoid this pattern;
    dispatch(registryActions.userLogin("username", "password"));
  };

  useEffect(() => {
    if (isLoggedin) {
      let { from } = location.state || { from: { pathname: "/" } };
      history.replace(from);
    }
  }, [isLoggedin]);
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
              onChange={(event, newValue) => setUsername(newValue)}
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
              onChange={(event, newValue) => setPassword(newValue)}
              mt="30px"
              fullWidth={true}
            />
          </Grid>
          <br />

          <Button
            label="Submit"
            onClick={(event) => handleSubmit(event)}
            color="secondary"
          >
            Go
          </Button>
          <Link to="./register">Register</Link>
        </Box>
      </ThemeProvider>
    </div>
  );
};

const style = {
  margin: 15,
};
export default withRouter(Login);
