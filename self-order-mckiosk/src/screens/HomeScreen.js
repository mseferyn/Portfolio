import React from 'react';
import { Box, Card, CardActionArea, Typography } from '@material-ui/core';
import TouchAppIcon from '@material-ui/icons/TouchApp';
import { useStyles } from '../styles';
import Logo from '../components/Logo';
import { useNavigate } from 'react-router-dom';

export default function HomeScreen() {
  const styles = useStyles();
  const navigate = useNavigate();

  const handleClick = () => {
    navigate('/choose');
  };

  return (
    <Card>
      <CardActionArea onClick={handleClick}>
        <Box className={[styles.root, styles.red]}>
          <Box className={[styles.main, styles.center]}>
            <Typography variant="h6" component="h6">
              Samoobsługowy kiosk
            </Typography>
            <Typography variant="h1" component="h1" className={styles.bold}>
              Zamów <br />
              i zapłać
              <br />
              tutaj
            </Typography>
            <TouchAppIcon fontSize="large"></TouchAppIcon>
          </Box>
          <Box className={[styles.center, styles.green]}>
            <Logo large />
            <Typography variant="h5" component="h5" className={styles.footer}>
              Touch to start
            </Typography>
          </Box>
        </Box>
      </CardActionArea>
    </Card>
  );
}
