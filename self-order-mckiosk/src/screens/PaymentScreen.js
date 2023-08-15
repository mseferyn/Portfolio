import { Box, Button, CircularProgress, Typography } from "@material-ui/core";
import React from "react";
import Logo from "../components/Logo";
import { useStyles } from "../styles";
import { useNavigate } from "react-router-dom";

export default function PaymentScreen() {
  const styles = useStyles();
  const navigate = useNavigate();

  return (
    <Box className={[styles.root, styles.navy]}>
      <Box className={[styles.main, styles.center]}>
        <Box>
          <Logo large></Logo>
          <Typography
            className={styles.title}
            gutterBottom
            variant="h3"
            component="h3"
          >
            Proszę postępować zgodnie z instukcjami na PIN padzie
          </Typography>
          <CircularProgress />
        </Box>
      </Box>
      <Box className={[styles.center, styles.space]}>
        <Button
          onClick={() => navigate("complete")}
          variant="contained"
          color="primary"
          className={styles.largeButton}
        >
          Zakończ Zamówienie
        </Button>
      </Box>
    </Box>
  );
}
