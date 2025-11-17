package com.bvd.java_fundamentals.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private String OrderId;
    private String CustomerId;
    private Date OrderDate;
    private String ProductName;
    private String Category;
    private double UnitPrice;
    private int Quantity;

    public Order(String orderId, String customerId, Date orderDate, String productName, String category, double unitPrice, int quantity) {
        this.OrderId = orderId;
        this.CustomerId = customerId;
        this.OrderDate = orderDate;
        this.ProductName = productName;
        this.Category = category;
        this.UnitPrice = unitPrice;
        this.Quantity = quantity;
    }

    public Order() {}

}
