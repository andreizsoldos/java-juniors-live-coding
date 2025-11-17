package com.bvd.java_fundamentals.model;

import java.util.Date;

public class Order {
    public String orderId;
    public String customerId;
    public Date orderDate;
    public String productName;
    public String category;
    public long unitPrice;
    public int quantity;

    public Order(String orderId, String customerId, Date orderDate,String productName, String category, long unitPrice, int quantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.productName = productName;
        this.category = category;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCategory() {
        return category;
    }

    public String getProductName() {
        return productName;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", productName='" + productName + '\''+
                ", category='" + category+ '\''+
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
    public double revenue(double unitPrice, int quantity){
        return unitPrice * quantity;
    }
}
