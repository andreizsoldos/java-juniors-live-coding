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

    // orderId,customerId,orderDate,productName,category,unitPrice,quantity
    //"O-1001,C-001,2025-10-01, USB-C Cable ,Accessories,9.99,2",
    public Order(String orderId, String customerId, LocalDate orderDate, String productName, String category, BigDecimal unitPrice, int quantity) {
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

    public String getCustomerId() {
        return customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal revenue() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
