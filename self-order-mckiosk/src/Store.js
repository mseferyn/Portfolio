import React, { createContext, useReducer } from "react";
import {
  CATEGORY_LIST_FAIL,
  CATEGORY_LIST_REQUEST,
  CATEGORY_LIST_SUCCESS,
  ORDER_ADD_ITEM,
  ORDER_CLEAR,
  ORDER_REMOVE_ITEM,
  ORDER_SET_PAYMENT_TYPE,
  ORDER_SET_TYPE,
  PRODUCT_LIST_FAIL,
  PRODUCT_LIST_REQUEST,
  PRODUCT_LIST_SUCCESS,
  ORDER_LIST_REQUEST,
  ORDER_LIST_SUCCESS,
  ORDER_LIST_FAIL,
} from "./constants";

export const Store = createContext();

const initialState = {
  categoryList: { loading: true },
  productList: { loading: true },

  order: {
    orderType: "Na miejscu",
    orderItems: [],
    paymentType: "Zapłać tutaj",
  },
  orderList: {
    orders: [],
    loading: false,
    error: null,
  },
};

function reducer(state, action) {
  switch (action.type) {
    case CATEGORY_LIST_REQUEST:
      return { ...state, categoryList: { loading: true } };

    case CATEGORY_LIST_SUCCESS:
      console.log("Przed aktualizacją:", state.categoryList);
      console.log("Nowe dane:", action.payload);
      return {
        ...state,
        categoryList: { loading: false, categories: action.payload },
      };

    case CATEGORY_LIST_FAIL:
      return {
        ...state,
        categoryList: { loading: false, error: action.payload },
      };

    case PRODUCT_LIST_REQUEST:
      return { ...state, productList: { loading: true } };

    case PRODUCT_LIST_SUCCESS:
      return {
        ...state,
        productList: { loading: false, products: action.payload },
      };

    case PRODUCT_LIST_FAIL:
      return {
        ...state,
        productList: { loading: false, error: action.payload },
      };

    case ORDER_SET_TYPE:
      return {
        ...state,
        order: { ...state.order, orderType: action.payload },
      };

    case ORDER_SET_PAYMENT_TYPE:
      return {
        ...state,
        order: { ...state.order, paymentType: action.payload },
      };

    case ORDER_ADD_ITEM: {
      const item = action.payload;
      const existItem = state.order.orderItems.find(
        (x) => x.name === item.name
      );
      const orderItems = existItem
        ? state.order.orderItems.map((x) =>
            x.name === existItem.name ? item : x
          )
        : [...state.order.orderItems, item];
      const itemsCount = orderItems.reduce((a, c) => a + c.quantity, 0);
      const itemsPrice = orderItems.reduce(
        (a, c) => a + c.quantity * c.price,
        0
      );

      return {
        ...state,
        order: {
          ...state.order,
          orderItems,
          itemsPrice,
          itemsCount,
        },
      };
    }

    case ORDER_REMOVE_ITEM: {
      const orderItems = state.order.orderItems.filter(
        (x) => x.name !== action.payload.name
      );
      const itemsCount = orderItems.reduce((a, c) => a + c.quantity, 0);
      const itemsPrice = orderItems.reduce(
        (a, c) => a + c.quantity * c.price,
        0
      );

      return {
        ...state,
        order: {
          ...state.order,
          orderItems,
          itemsPrice,
          itemsCount,
        },
      };
    }

    case ORDER_CLEAR:
      return {
        ...state,
        order: {
          orderItems: [],
          itemsPrice: 0,
          itemsCount: 0,
        },
      };

    case ORDER_LIST_REQUEST:
      return {
        ...state,
        orderList: { ...state.orderList, loading: true },
      };

    case ORDER_LIST_SUCCESS:
      return {
        ...state,
        orderList: { loading: false, orders: action.payload, error: null },
      };

    case ORDER_LIST_FAIL:
      return {
        ...state,
        orderList: { loading: false, orders: [], error: action.payload },
      };

    default:
      return state;
  }
}

export function StoreProvider(props) {
  const [state, dispatch] = useReducer(reducer, initialState);
  const value = { state, dispatch };

  return <Store.Provider value={value}>{props.children}</Store.Provider>;
}
