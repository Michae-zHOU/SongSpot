import React, { useState } from "react";
// import AppBar from "@material-ui/core/AppBar";
// import Toolbar from "@material-ui/core/Toolbar";
// import Typography from "@material-ui/core/Typography";
// import Button from "@material-ui/core/Button";
// import Grid from "@material-ui/core/Grid";
// import "./Navbar.scss";
// import { theme } from "../colorPlatte";
// import { ThemeProvider } from "@material-ui/core/styles";
// import { Link } from "react-router-dom";
// import Container from "@material-ui/core/Container";
import { Drawer, List, ListItem, ListItemText } from "@material-ui/core";
// import {List,ListItem}
// {/* <List>
// {['Inbox', 'Starred', 'Send email', 'Drafts'].map((text, index) => (
//   <ListItem button key={text}>
//     <ListItemIcon>{index % 2 === 0 ? <InboxIcon /> : <MailIcon />}</ListItemIcon>
//     <ListItemText primary={text} />
//   </ListItem>
// ))}
// </List>
// <Divider /> */}
export const CustomDrawer = props => {
  const anchor = "left";
  const [drawerOpen, setDrawerOpen] = useState(true);

  return (
    <Drawer anchor={anchor} open={drawerOpen}>
      <List>
        {[
          "Submit a Song",
          "My Submissions",
          "Workspace",
          "Blogs & News",
          "Chat"
        ].map(text => (
          <ListItem button key={text}>
            <ListItemText primary={text} />
          </ListItem>
        ))}
      </List>
    </Drawer>
  );
};
