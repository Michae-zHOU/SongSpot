import React, { useState } from "react";
import {
  Drawer,
  List,
  ListItem,
  ListItemText,
  Divider,
} from "@material-ui/core";
import { Link } from "react-router-dom";

export const ArtistNavDrawer = (props) => {
  const ArtistNavList = [
    { value: "submit", label: "Submit a Song" },
    { value: "submissions", label: "My Submissions" },
    { value: "workspace", label: "Workspace" },
    { value: "blogs-news", label: "Blogs & News" },
    { value: "chat", label: "Chat" },
  ];
  const [selectedListItem, setSelectedListItem] = useState("dashboard");
  const handleListItemClick = (value) => {
    // console.log(value, e);
    // debugger;
    setSelectedListItem(value);
  };
  return (
    <div className="drawer__artistNav">
      <Link to={`/artist`}>
        <ListItem button key={"Artist"}>
          <ListItemText
            primary={"Dashboard"}
            onClick={() => handleListItemClick("dashboard")}
            selected={selectedListItem === "dashboard"}
          />
        </ListItem>
      </Link>
      <Divider />
      <List>
        {ArtistNavList.map((text) => (
          <Link to={`/artist/${text.value}`}>
            <ListItem
              button
              key={text.value}
              onClick={() => handleListItemClick(text.value)}
              selected={selectedListItem === text.value}
            >
              <ListItemText primary={text.label} />
            </ListItem>
          </Link>
        ))}
      </List>
    </div>
  );
};
