import { combineReducers } from "redux";
import { RegistryData } from "./RegistryReducer";
import { ArtistData } from "./ArtistReducer";
import { CuratorData } from "./CuratorReducer";
import { AppData } from "./AppReducer";
export const appReducer = combineReducers({
  RegistryData,
  ArtistData,
  CuratorData,
  AppData,
});
