import React, { useContext, useEffect } from "react";
import { Store } from "../Store";
import { listOrders, deliverOrder } from "../actions";

const AdminScreen = () => {
  const { state, dispatch } = useContext(Store);
  const { orders, loading, error } = state.orderList;

  useEffect(() => {
    console.log("Wywołano useEffect w AdminScreen");
    listOrders(dispatch)
      .then(() => console.log("Pobrano zamówienia"))
      .catch((error) =>
        console.log("Błąd podczas pobierania zamówień:", error)
      );
  }, [dispatch]);

  const deliverOrderHandler = (orderId) => {
    deliverOrder(dispatch, orderId);
  };

  console.log(orders);
  console.log("Renderowanie komponentu AdminScreen");

  return (
    <div>
      <h1>Panel Admina</h1>
      {loading ? (
        <p>Ładowanie zamówień...</p>
      ) : error ? (
        <p>Błąd: {error}</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>ID zamówienia</th>
              <th>Status zamówienia</th>
              <th>Akcje</th>
            </tr>
          </thead>
          <tbody>
            {orders.map((order) => (
              <tr key={order.id}>
                <td>{order.id}</td>
                <td>{order.status}</td>
                <td>
                  {order.status === "Przyjęte" && (
                    <button onClick={() => deliverOrderHandler(order.id)}>
                      Dostarczone
                    </button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default AdminScreen;
