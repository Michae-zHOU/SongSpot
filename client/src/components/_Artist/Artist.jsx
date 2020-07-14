import React from "react";
import "./Artist.scss";
import { Link } from "react-router-dom";
import Container from "@material-ui/core/Container";
import { Paper, Grid, Card, CardMedia } from "@material-ui/core";
export const Artist = (props) => {
  const { songs } = props;
  return (
    <div className="dashboard artist">
      <Grid sm="6"></Grid>
      <Grid sm="6">
        {songs.map((song) => (
          <Card classname="artist__songList" raised>
            <CardMedia height="140"></CardMedia>
            {song.name}
            {song.likes}
            {song.dislikes}
          </Card>
        ))}
      </Grid>
    </div>
  );
};
