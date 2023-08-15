import React from "react";
import { Container, createTheme, CssBaseline, Paper } from "@mui/material";
import { ThemeProvider } from "@mui/styles";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Helmet } from "react-helmet";
import HomeScreen from "./screens/HomeScreen";
import ChooseScreen from "./screens/ChooseScreen";
import { StoreProvider } from "./Store";
import OrderScreen from "./screens/OrderScreen";
import ReviewScreen from "./screens/ReviewScreen";
import SelectPaymentScreen from "./screens/SelectPaymentScreen";
import PaymentScreen from "./screens/PaymentScreen";
import CompleteOrderScreen from "./screens/CompleteOrderScreen";
import AdminScreen from "./screens/AdminScreen";

const theme = createTheme({
  // konfiguracja motywu
});

function App() {
  return (
    <BrowserRouter>
      <Helmet>
        <title>Self-Order Kiosk</title>
      </Helmet>

      <ThemeProvider theme={theme}>
        <CssBaseline />
        <Container maxWidth="lg">
          <Paper>
            <StoreProvider>
              <Routes>
                <Route path="/" element={<HomeScreen />} exact={true} />
                <Route path="/choose" element={<ChooseScreen />} exact={true} />
                <Route path="/order" element={<OrderScreen />} exact={true} />
                <Route path="/review" element={<ReviewScreen />} exact={true} />
                <Route
                  path="/select-payment"
                  element={<SelectPaymentScreen />}
                  exact={true}
                />
                <Route
                  path="/payment"
                  element={<PaymentScreen />}
                  exact={true}
                />
                <Route
                  path="/complete"
                  element={<CompleteOrderScreen />}
                  exact={true}
                />
                <Route
                  path="/payment/complete"
                  element={<CompleteOrderScreen />}
                  exact={true}
                />
                <Route path="/admin" element={<AdminScreen />} />
              </Routes>
            </StoreProvider>
          </Paper>
        </Container>
      </ThemeProvider>
    </BrowserRouter>
  );
}

export default App;
