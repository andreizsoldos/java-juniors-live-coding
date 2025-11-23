package com.bvd.java_fundamentals.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    private String orderId;
    private String customerId;
    private LocalDate orderDate;
    private String productName;
    private String category;
    private BigDecimal unitPrice;
    private int quantity;

    public Order(String orderId, String customerId, LocalDate orderDate, String productName, String category, BigDecimal price, int quantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.productName = productName;
        this.category = category;
        this.unitPrice = price;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public BigDecimal getRevenue() {
        return unitPrice.multiply(new BigDecimal(quantity));
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDate=" + orderDate +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
