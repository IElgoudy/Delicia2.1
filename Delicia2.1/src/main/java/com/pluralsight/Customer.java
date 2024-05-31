package com.pluralsight;

import Service.Order;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private List<Order> orderList;

    public Customer() {
        this.orderList = new ArrayList<>();
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void addOrder(Order order) {
        this.orderList.add(order);
    }
}

