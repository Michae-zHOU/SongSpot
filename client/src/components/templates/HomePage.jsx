import React, { Suspense } from "react";
import Grid from "@material-ui/core/Grid";
import { Container } from "@material-ui/core";
import "./HomePage.scss";
import Typography from "@material-ui/core/Typography";

export const HomePage = () => {
  return (
    <div className="homepage">
      <div className="homepage__intro">
        <div className="homepage__title">
          <Typography variant="h3">Welcome to Song Spot</Typography>
        </div>
        <div className="homepage__subtitle">
          <Typography className="homepage__subtitle" variant="h4" color="light">
            Explore the True Power to Gain Influence of Your Music
          </Typography>
        </div>
      </div>
    </div>
  );
};
