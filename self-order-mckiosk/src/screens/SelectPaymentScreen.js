import { Box, Card, CardMedia } from "@material-ui/core";
import { CardActionArea, CardContent, Typography } from "@mui/material";
import React, { useContext } from "react";
import { setPaymentType } from "../actions";
import Logo from "../components/Logo";
import { Store } from "../Store";
import { useStyles } from "../styles";
import { useNavigate } from "react-router-dom";

export default function SelectPaymentScreen() {
  const { dispatch } = useContext(Store);
  const styles = useStyles();
  const navigate = useNavigate();

  const selectHandler = (paymentType) => {
    setPaymentType(dispatch, paymentType);
    if (paymentType === "Zapłać tutaj") {
      navigate("/payment");
    } else {
      navigate("/complete");
    }
  };

  return (
    <Box className={[styles.root, styles.navy]}>
      <Box className={[styles.main, styles.center]}>
        <Logo large></Logo>
        <Typography
          className={styles.center}
          gutterBottom
          variant="h3"
          component="h3"
        >
          Metoda Płatności
        </Typography>
      </Box>
      <Box className={styles.cards}>
        <Card className={[styles.card, styles.space]}>
          <CardActionArea onClick={() => selectHandler("Zapłać tutaj")}>
            <CardMedia
              component="img"
              alt="Zapłać tutaj"
              image="/images/payhere.png"
              className={styles.media}
            />
          </CardActionArea>
          <CardContent>
            <Typography
              className={styles.center}
              gutterBottom
              variant="h6"
              color="textPrimary"
              component="p"
            >
              Zapłać Tutaj
            </Typography>
          </CardContent>
        </Card>
        <Card className={[styles.card, styles.space]}>
          <CardActionArea onClick={() => selectHandler("Zapłać Przy Kasie")}>
            <CardMedia
              component="img"
              alt="Zapłać tutaj"
              image="/images/atcounter.png"
              className={styles.media}
            />
          </CardActionArea>
          <CardContent>
            <Typography
              className={styles.center}
              gutterBottom
              variant="h6"
              color="textPrimary"
              component="p"
            >
              Zapłać Przy Kasie
            </Typography>
          </CardContent>
        </Card>
      </Box>
    </Box>
  );
}
