package com.bvd.java_fundamentals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


public class Order {
    private final String orderId;
    private final String customerId;
    private final LocalDate orderDate;
    private final String productName;
    private final String category;
    private final BigDecimal unitPrice;
    private final int quantity;

    public Order(String orderId, String customerId, LocalDate orderDate, String productName, String category, BigDecimal unitPrice, int quantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.productName = productName;
        this.category = category;
        this.unitPrice = unitPrice;
        this.quantity = quantity;


    }

    public String getOrderId() { return orderId; }
     public String getCustomerId() { return customerId; }
    public LocalDate getOrderDate() { return orderDate; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }


    public BigDecimal getRevenue() {
        return unitPrice.multiply(new BigDecimal(quantity));
    }

    @Override
    public String toString() {
        return "Order(orderId=%s, customerId=%s, orderDate=%s,\n        productName=%s, category=%s, unitPrice=%s, quantity=%s)".formatted(
                orderId, customerId, orderDate, productName, category, unitPrice, quantity);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return quantity == order.quantity && Objects.equals(orderId, order.orderId) && Objects.equals(customerId, order.customerId) && Objects.equals(orderDate, order.orderDate) && Objects.equals(productName, order.productName) && Objects.equals(category, order.category) && Objects.equals(unitPrice, order.unitPrice);
    }
    @Override
    public int hashCode() {
        return Objects.hash(orderId, customerId, orderDate, productName, category, unitPrice, quantity);
    }
}