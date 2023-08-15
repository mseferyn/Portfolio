import React, { useContext, useEffect, useState } from "react";
import {
  addToOrder,
  clearOrder,
  listCategories,
  listProducts,
  removeFromOrder,
} from "../actions";
import { Store } from "../Store";
import {
  Avatar,
  Box,
  Card,
  CardContent,
  CircularProgress,
  Grid,
  List,
  ListItem,
} from "@material-ui/core";
import {
  Alert,
  Button,
  CardActionArea,
  CardMedia,
  Dialog,
  DialogTitle,
  TextField,
  Typography,
} from "@mui/material";
import AddIcon from "@material-ui/icons/Add";
import RemoveIcon from "@material-ui/icons/Remove";
import Logo from "../components/Logo";
import { useStyles } from "../styles";
import { useNavigate } from "react-router-dom";

export default function OrderScreen() {
  const styles = useStyles();
  const { state, dispatch } = useContext(Store);
  const { categories, loading, error } = state.categoryList;
  const { productList } = state;
  const {
    products,
    loading: loadingProducts,
    error: errorProducts,
  } = productList || {};
  const { orderItems, itemsCount, itemsPrice, orderType } = state.order;
  const [categoryName, setCategoryName] = useState("");
  const [quantity, setQuantity] = useState(1);
  const [isOpen, setIsOpen] = useState(false);
  const [product, setProduct] = useState({});
  const navigate = useNavigate();

  const closeHandler = () => {
    setIsOpen(false);
  };

  const productClickHandler = (p) => {
    setProduct(p);
    setIsOpen(true);
  };

  const handleQuantityDecrease = () => {
    if (quantity > 1) {
      setQuantity(quantity - 1);
    }
  };

  const handleQuantityIncrease = () => {
    setQuantity(quantity + 1);
  };

  const addToOrderHandler = async () => {
    await addToOrder(dispatch, { ...product, quantity });
    setIsOpen(false);
  };

  const cancelOrRemoveFromOrder = () => {
    removeFromOrder(dispatch, product);
    setIsOpen(false);
  };

  useEffect(() => {
    if (!categories) {
      listCategories(dispatch);
    }
    if (!products && categoryName) {
      listProducts(dispatch, categoryName).catch((error) => {
        console.error("Error retrieving products:", error);
      });
    }
  }, [dispatch, categoryName, categories]);

  const categoryClickHandler = (name) => {
    setCategoryName(name);
    listProducts(dispatch, name).catch((error) => {
      console.error("Error retrieving products:", error);
    });
  };

  const previewOrderHandler = () => {
    navigate("/review");
  };

  return (
    <Box className={styles.root}>
      <Dialog
        maxWidth="sm"
        fullWidth={true}
        open={isOpen}
        onClose={closeHandler}
      >
        <DialogTitle className={styles.center}>Add {product.name}</DialogTitle>
        <Box className={[styles.row, styles.center]}>
          <Button
            variant="contained"
            color="primary"
            disabled={quantity === 1}
            onClick={handleQuantityDecrease}
          >
            <RemoveIcon />
          </Button>
          <TextField
            inputProps={{ className: styles.largeInput }}
            InputProps={{
              bar: true,
              inputProps: {
                className: styles.largeInput,
              },
            }}
            className={styles.largeNumber}
            type="number"
            variant="filled"
            min={1}
            value={quantity}
          />
          <Button
            variant="contained"
            color="primary"
            onClick={handleQuantityIncrease}
          >
            <AddIcon />
          </Button>
        </Box>
        <Box className={[styles.row, styles.around]}>
          <Button
            onClick={cancelOrRemoveFromOrder}
            variant="contained"
            color="primary"
            size="large"
            className={styles.largeButton}
          >
            {orderItems.find((x) => x.name === product.name)
              ? "Usuń z zamówienia"
              : "Anuluj"}
          </Button>
          <Button
            onClick={addToOrderHandler}
            variant="contained"
            color="primary"
            size="large"
            className={styles.largeButton}
          >
            Dodaj Do Zamówienia
          </Button>
        </Box>
      </Dialog>
      <Grid container>
        <Grid item md={2}>
          <List>
            {loading ? (
              <CircularProgress />
            ) : error ? (
              <Alert severity="error">{error}</Alert>
            ) : (
              <>
                <ListItem></ListItem>
                <ListItem onClick={() => categoryClickHandler("")} button>
                  <Logo large />
                </ListItem>
                {categories &&
                  categories.map((category) => (
                    <ListItem
                      button
                      key={category.name}
                      onClick={() => categoryClickHandler(category.name)}
                    >
                      <Avatar alt={category.name} src={category.image} />
                    </ListItem>
                  ))}
              </>
            )}
          </List>
        </Grid>
        <Grid item md={10}>
          <Typography
            gutterBottom
            className={styles.title}
            variant="h2"
            component="h2"
          >
            {categoryName || "Main Menu"}
          </Typography>
          <Grid container spacing={1}>
            {loadingProducts ? (
              <CircularProgress />
            ) : errorProducts ? (
              <Alert severity="error">{errorProducts}</Alert>
            ) : (
              products &&
              products.map((product) => (
                <Grid item md={5} key={product.id}>
                  <Card
                    className={styles.productcard}
                    onClick={() => productClickHandler(product)}
                  >
                    <CardActionArea>
                      <CardMedia
                        component="img"
                        alt={product.name}
                        image={product.image}
                        className={styles.media}
                      />
                    </CardActionArea>
                    <CardContent>
                      <Typography
                        gutterBottom
                        variant="body2"
                        color="textPrimary"
                        component="p"
                      >
                        {product.name}
                      </Typography>
                      <Box className={styles.cardFootter}>
                        <Typography
                          variant="body2"
                          color="textSecondary"
                          component="p"
                        >
                          {product.calorie} Cal
                        </Typography>
                        <Typography
                          variant="body2"
                          color="textPrimary"
                          component="p"
                        >
                          {product.price} zł
                        </Typography>
                      </Box>
                    </CardContent>
                  </Card>
                </Grid>
              ))
            )}
          </Grid>
        </Grid>
      </Grid>
      <Box>
        <Box>
          <Box className={[styles.bordered, styles.space]}>
            Moje Zamówienie - {orderType} | Cena: zł{itemsPrice} | Ilość:{" "}
            {itemsCount}
          </Box>
          <Box>
            <Button
              onClick={() => {
                clearOrder(dispatch);
                navigate("/");
              }}
              variant="contained"
              color="primary"
              className={styles.largeButton}
            >
              Anuluj Zamówienie
            </Button>
            <Button
              onClick={previewOrderHandler}
              variant="contained"
              color="primary"
              disabled={orderItems.length === 0}
              className={styles.largeButton}
            >
              Akceptuj
            </Button>
          </Box>
        </Box>
      </Box>
    </Box>
  );
}
