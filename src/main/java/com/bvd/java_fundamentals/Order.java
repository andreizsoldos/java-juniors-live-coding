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
    private final BigDecimal unitPrice; // money -> BigDecimal
    private final int quantity;

    public Order(String orderId,
                 String customerId,
                 LocalDate orderDate,
                 String productName,
                 String category,
                 BigDecimal unitPrice,
                 int quantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.productName = productName;
        this.category = category;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getOrderId()     { return orderId; }
    public String getCustomerId()  { return customerId; }
    public LocalDate getOrderDate(){ return orderDate; }
    public String getProductName() { return productName; }
    public String getCategory()    { return category; }
    public BigDecimal getUnitPrice(){ return unitPrice; }
    public int getQuantity()       { return quantity; }

    public BigDecimal totalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        String text = "Order Details:";
        text += "  Order ID: " + orderId ;
        text += "  Customer ID: " + customerId ;
        text += "  Order Date: " + orderDate ;
        text += "  Product: " + productName ;
        text += "  Category: " + category ;
        text += "  Unit Price: " + unitPrice ;
        text += "  Quantity: " + quantity ;
        text += "  Total: " + totalPrice() ;
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
