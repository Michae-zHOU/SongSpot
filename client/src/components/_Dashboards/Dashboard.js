import React, { Suspense } from "react";
import Grid from "@material-ui/core/Grid";
import { Container } from "@material-ui/core";
import "./HomePage.scss";
import Typography from "@material-ui/core/Typography";
import { ArtistContainer } from "../../containers/ArtistContainer";
import { CuratorContainer } from "../../containers/CuratorConatainer";
export const Dashboard = () => {
  return (
    <div className="dashboard">
      <ArtistContainer />
      {/* <CuratorContainer /> */}
    </div>
  );
};
