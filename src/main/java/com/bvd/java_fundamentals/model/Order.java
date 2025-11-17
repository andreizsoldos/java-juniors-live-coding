package com.bvd.java_fundamentals.model;

import java.util.Date;

public class Order {
    private String orderId;
    private String customerId;
    private Date orderDate;
    private String productName;
    private String category;
    private double unitPrice;
    private int quantity;
    private double revenue;

    public Order(String orderId, String customerId, Date orderDate, String productName, String category,
                 double unitPrice, int quantity, double revenue) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.productName = productName;
        this.category = category;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.revenue = revenue; // sau unitPrice * quantity;
    }

    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public double getRevenue() { return revenue; }


    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String toString(){

    return String.format("Order(orderId=%s, customerId=%s, orderDate=%s,productName=%s, category=%s, unitPrice=%f, quantity=%d)", orderId, customerId, orderDate, productName, category, unitPrice, quantity);
    }

    public Date getOrderDate() {
        return  orderDate;
    }
}

