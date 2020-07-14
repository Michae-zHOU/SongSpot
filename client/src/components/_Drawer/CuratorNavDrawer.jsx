import React from "react";
import { Drawer, List, ListItem, ListItemText } from "@material-ui/core";

export const CuratorNavDrawer = (props) => {
  const curatorNavList = ["Pots", "Approval Lists", "Blogs & News"];
  return (
    <div className="drawer__curatorNav">
      <List>
        {curatorNavList.map((text) => (
          <ListItem button key={text}>
            <ListItemText primary={text} />
          </ListItem>
        ))}
      </List>
    </div>
  );
};
