import { makeStyles } from "@material-ui/core";
import { height } from "@mui/system";

export const useStyles = makeStyles((theme) => ({
  root: {
    height: "auto",
    minHeight: "100vh",
    display: "flex",
    flexDirection: "column",
  },
  red: {
    backgroundColor: "#ff2040",
    color: "#ffffff",
  },
  navy: {
    backgroundColor: "#003080",
  },

  main: {
    flex: 1,
    overflow: "auto",
    flexDirection: "column",
    display: "flex",
    color: "#ffffff",
  },
  center: {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    textAlign: "center",
  },
  green: {
    backgroundColor: "#00b020",
  },
  largeLogo: {
    height: 100,
  },
  logo: {
    height: 50,
  },
  cards: {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
  },
  card: {
    margin: 10,
  },
  productcard: {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    width: "400px",
    height: "400px",
  },
  title: {
    marginTop: 18,
  },
  space: {
    padding: 10,
  },
  media: {
    width: 200,
  },
  largeButton: {
    width: 250,
  },
  largeInput: {
    width: "60px!important",
    padding: "0!important",
    fontSize: "35px!important",
    textAlign: "center!important",
  },
  bordered: {
    borderWidth: 2,
    borderRadius: 5,
    margin: 5,
    borderStyle: "solid",
  },
  row: {
    display: "flex",
    padding: 10,
  },
  around: {
    justifyContent: "space-around",
  },
  between: {
    justifyContent: "space-between",
  },
  column: { flexDirection: "column" },
}));
