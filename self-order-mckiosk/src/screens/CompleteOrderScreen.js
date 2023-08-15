import React, { useEffect, useState } from "react";
import { Box, Typography } from "@material-ui/core";
import Logo from "../components/Logo";
import { useStyles } from "../styles";

export default function CompleteOrderScreen() {
  const styles = useStyles();
  const [orderNumber] = useState(Math.floor(Math.random() * 30) + 1); // Generowanie losowej liczby od 1 do 30

  const waitingTimeMinutes = Math.floor(Math.random() * 16) + 5; // Generowanie losowej liczby od 5 do 20 (w minutach)
  const waitingTimeSeconds = waitingTimeMinutes * 60; // Przeliczenie czasu oczekiwania na sekundy

  const [remainingTime, setRemainingTime] = useState(waitingTimeSeconds);

  useEffect(() => {
    const timer = setInterval(() => {
      setRemainingTime((prevTime) => prevTime - 1);
    }, 1000); // Co sekundę zmniejszamy pozostały czas o 1

    return () => {
      clearInterval(timer); // Czyszczenie timera przy odmontowywaniu komponentu
    };
  }, []);

  const formatTime = (time) => {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;

    return `${minutes.toString().padStart(2, "0")}:${seconds
      .toString()
      .padStart(2, "0")}`;
  };

  return (
    <Box className={[styles.root, styles.navy]}>
      <Box className={[styles.main, styles.center]}>
        <Logo large />
        <Typography className={styles.title} variant="h3" component="h3">
          Twoje zamówienie zostało przyjęte!
        </Typography>
        <Typography variant="h5" component="h5">
          Numer zamówienia: {orderNumber}
        </Typography>
        <Typography variant="h5" component="h5">
          Czas oczekiwania: {formatTime(remainingTime)}
        </Typography>
        {/* Dodaj tutaj dowolne inne elementy graficzne lub informacje */}
      </Box>
    </Box>
  );
}
