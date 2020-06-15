import React, { Component } from "react";
import { ThemeProvider } from "@material-ui/core/styles";
// import AppBar from "material-ui/AppBar";
// import RaisedButton from "material-ui/RaisedButton";
// import TextField from "material-ui/TextField";
import {
  Button,
  AppBar,
  TextField,
  Box,
  Grid,
  Typography
} from "@material-ui/core";
import axios from "axios";
import "./Register.scss";
class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      first_name: "",
      last_name: "",
      email: "",
      password: ""
    };
  }
  render() {
    return (
      <div className="register">
        <ThemeProvider>
          <div>
            <Box
              boxShadow={1}
              p={5}
              style={{ width: "30rem", height: "30rem" }}
            >
              <Grid justify="left" margin="110" className="login__title">
                <Typography variant="h5" align="left">
                  Register
                </Typography>
              </Grid>
              <Grid>
                <TextField
                  hintText="Enter your First Name"
                  id="standard-basic"
                  label="Firstname"
                  floatingLabelText="First Name"
                  onChange={(event, newValue) =>
                    this.setState({ first_name: newValue })
                  }
                />
              </Grid>
              <br />

              <Grid>
                <TextField
                  hintText="Enter your Last Name"
                  floatingLabelText="Last Name"
                  label="Lastname"
                  onChange={(event, newValue) =>
                    this.setState({ last_name: newValue })
                  }
                />
              </Grid>
              <br />
              <Grid>
                <TextField
                  hintText="Enter your Email"
                  type="email"
                  floatingLabelText="Email"
                  label="Email"
                  onChange={(event, newValue) =>
                    this.setState({ email: newValue })
                  }
                />{" "}
              </Grid>
              <br />
              <Grid>
                <TextField
                  type="password"
                  label="Password"
                  hintText="Enter your Password"
                  floatingLabelText="Password"
                  onChange={(event, newValue) =>
                    this.setState({ password: newValue })
                  }
                />
              </Grid>
              <Grid>
                <br />
                <Button
                  label="Submit"
                  primary={true}
                  onClick={event => this.handleClick(event)}
                />{" "}
              </Grid>
            </Box>
          </div>
        </ThemeProvider>
      </div>
    );
  }
}

export default Register;
