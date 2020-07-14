import React, { Component, useState, useEffect } from "react";
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
  Typography,
  Chip,
  Paper,
} from "@material-ui/core";
import axios from "axios";
import "./Register.scss";

// Rock,
// Hip hop music,
// Jazz,
// Blues,
// Pop
// Folk,
// Heavy metal
// Rhythm and blues
// Musical theatre
// Country
// Classical
// Soul
// Punk rock
// Reggae
// Electronic
// Funk
// Electronic dance
// Singing
// House
// Disco

// Electronic dance
// Singing
// House
// Disco
// Techno
// Popular
// Alternative rock
// Gospel
// Orchestra
// Swing
// Trance
// Dance
// Dubstep
// Ambient
// Psychedelic
// Instrumental
// Industrial
// Electro,
// Grunge,
// Indie rock,
// Opera,
// World,
// Breakbeat,
// Progressive rock,
// New wave,
// Emo,
// Drum and bass,
// Poprock,
// Hardcore,
// Baroque,
// Medieval,
// Bluegrass,

const musicGenre = [
  "House",
  "Disco",
  "Techno",
  "Popular",
  "Gospel",
  "Orchestra",
  "Swing",
  "Trance",
  "Dance",
  "Dubstep",
  "Ambient",
  "Jazz",
  "Psychedelic",
  "Instrumental",
  "Industrial",
  "Electro",
  "Grunge",
];
const updateListWithoutDuplication = (arr, value) => {
  const updator = arr.slice();
  const ind = updator.indexOf(value);
  if (ind > -1) {
    updator.splice(ind, 1);
  } else {
    updator.push(value);
  }
  return updator;
};
const Register = () => {
  const initalState = {
    first_name: "",
    last_name: "",
    email: "",
    password: "",
    selectedGenre: [],
  };
  const [registerInfo, setRegisterInfo] = useState(initalState);

  const [registerGenre, setRegisterGenre] = useState([]);

  useEffect(() => {
    setRegisterGenre(musicGenre);
  }, [musicGenre]);
  const handleChipClick = (e) => {
    const selectedChip = e.target.innerText;
    const { selectedGenre } = registerInfo;
    const updator = updateListWithoutDuplication(selectedGenre, selectedChip);
    setRegisterInfo({ ...registerInfo, selectedGenre: updator });
  };

  const handleEnter = (e) => {
    const { charCode, target } = e;
    const { value } = target;

    if (charCode === 13) {
      const updator = updateListWithoutDuplication(registerGenre, value);
      setRegisterGenre(updator);
    }
  };
  const handleClear = () => {
    setRegisterInfo(initalState);
    setRegisterGenre(musicGenre);
  };
  return (
    <div className="register">
      <ThemeProvider>
        <Box boxShadow={1} p={5} style={{ width: "80rem", height: "40rem" }}>
          <Grid container justify="left" margin="110" className="login__title">
            <Grid md="6">
              <Grid md="12">
                <Typography variant="h5" align="left">
                  Register
                </Typography>
              </Grid>
              <Grid md="12">
                <TextField
                  hintText="Enter your First Name"
                  id="standard-basic"
                  label="Firstname"
                  size="medium"
                  floatingLabelText="First Name"
                  onChange={(event, newValue) =>
                    setRegisterInfo({ ...registerInfo, first_name: newValue })
                  }
                  fullWidth
                  margin="normal"
                />
              </Grid>
              <br />
              <Grid md="12">
                <TextField
                  hintText="Enter your Last Name"
                  floatingLabelText="Last Name"
                  label="Lastname"
                  onChange={(event, newValue) =>
                    setRegisterInfo({ ...registerInfo, last_name: newValue })
                  }
                  fullWidth
                  margin="normal"
                />
              </Grid>
              <br />
              <Grid md="12">
                <TextField
                  hintText="Enter your Email"
                  type="email"
                  floatingLabelText="Email"
                  label="Email"
                  onChange={(event, newValue) =>
                    setRegisterInfo({ ...registerInfo, email: newValue })
                  }
                  fullWidth
                  margin="normal"
                />
              </Grid>
              <br />
              <Grid md="12">
                <TextField
                  type="password"
                  label="Password"
                  hintText="Enter your Password"
                  floatingLabelText="Password"
                  onChange={(event, newValue) =>
                    setRegisterInfo({ ...registerInfo, password: newValue })
                  }
                  fullWidth
                  margin="normal"
                />
              </Grid>
              <br />
            </Grid>
            <Grid md="6" className="register__musicGenre">
              {registerGenre.map((genre) => (
                <Chip
                  label={genre}
                  margin="10"
                  clickable
                  onClick={handleChipClick}
                  color={
                    registerInfo.selectedGenre.includes(genre) ? "primary" : ""
                  }
                  key={genre}
                />
              ))}
              <Grid className="register__customGenre" md="6">
                <TextField
                  hintText="genre"
                  id="standard-basic"
                  label="customize genre"
                  size="small"
                  floatingLabelText="customize genre"
                  onChange={(event, newValue) =>
                    setRegisterInfo({ ...registerInfo, first_name: newValue })
                  }
                  onKeyPress={handleEnter}
                  fullWidth
                />
              </Grid>
            </Grid>
          </Grid>
          <Grid className="register__submit">
            <Button variant="outlined" color="primary">
              Submit
            </Button>
            <Button variant="outlined" primary={true} onClick={handleClear}>
              Clear
            </Button>
          </Grid>
        </Box>
      </ThemeProvider>
    </div>
  );
};

export default Register;
