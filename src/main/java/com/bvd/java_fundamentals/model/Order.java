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

    public Order (
            String orderId,
            String customerId,
            LocalDate orderDate,
            String productName,
            String category,
            BigDecimal unitPrice,
            int quantity
    ) {
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

    public double getRevenue() {
        return unitPrice.doubleValue() * quantity;
    }

}
