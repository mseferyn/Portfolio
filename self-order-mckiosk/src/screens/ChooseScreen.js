import React, { useContext } from 'react';
import { Box, Card, CardActionArea, CardContent, CardMedia, Fade, Typography } from '@mui/material';
import Logo from '../components/Logo';
import { useStyles } from '../styles';
import { setOrderType } from '../actions';
import {Store} from '../Store';
import { useNavigate } from 'react-router-dom';


export default function ChooseScreen() {

  const styles = useStyles();
  const { state, dispatch } = useContext(Store);
  const navigate = useNavigate();

  const chooseHandler = (orderType) => {
    setOrderType(dispatch, orderType);
    navigate('/order');
  };

  return (
    <Fade in={true}>
      <Box className={[styles.root, styles.navy]}>
        <Box className={[styles.main, styles.center]}>
          <Logo large />
          <Typography variant="h3" component="h3" className={styles.center} gutterBottom>
            Gdzie chciałbyś zjeść?
          </Typography>
          <Box className={styles.cards}>
            <Card className={[styles.card, styles.space]}>
              <CardActionArea onClick={() => chooseHandler('Na miejscu')}>
                <CardMedia
                  component="img"
                  alt="Na miejscu"
                  image="\images\namiejscu.png"
                  className={styles.media}
                />
                <CardContent>
                  <Typography gutterBottom variant="h4" color="textPrimary" component="p">
                    Na miejscu
                  </Typography>
                </CardContent>
              </CardActionArea>
            </Card>
            <Card className={[styles.card, styles.space]}>
              <CardActionArea onClick={() => chooseHandler('Na wynos')}>
                <CardMedia
                  component="img"
                  alt="Na wynos"
                  image="\images\nawynos.png"
                  className={styles.media}
                />
                <CardContent>
                  <Typography gutterBottom variant="h4" color="textPrimary" component="p">
                    Na wynos
                  </Typography>
                </CardContent>
              </CardActionArea>
            </Card>
          </Box>
        </Box>
      </Box>
    </Fade>
  );
}
