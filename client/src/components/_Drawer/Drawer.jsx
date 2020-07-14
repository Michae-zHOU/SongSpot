import React, { useState, Component } from "react";

import { Drawer, List, ListItem, ListItemText } from "@material-ui/core";
import { ArtistNavDrawer } from "./ArtistNavDrawer";
import { CuratorNavDrawer } from "./CuratorNavDrawer";
import "./Drawer.scss";
const DrawerNameSpace = {
  artistNav: ArtistNavDrawer,
  curatorNav: CuratorNavDrawer,
};
export const CustomDrawer = (props) => {
  const anchor = "left";
  const { drawerList, options } = props;

  return (
    <>
      {drawerList.map((drawer) => {
        const DrawerContent = DrawerNameSpace[drawer];
        return (
          <Drawer
            variant="persistent"
            anchor="left"
            open={drawerList.length > 0}
            className="drawer"
          >
            <DrawerContent />
          </Drawer>
        );
      })}
    </>
  );
};
