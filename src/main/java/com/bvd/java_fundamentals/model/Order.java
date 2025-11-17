package com.bvd.java_fundamentals.model;

import java.time.LocalDate;
import java.util.Date;

//Note: category could be an Enum
public class Order {

    public String getOrderID() {
        return orderID;
    }

    public String getCustomerID() {
        return customerID;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    private final String orderID;
    private final String customerID;
    private final LocalDate orderDate;
    private final String productName;
    private final String category;
    private final double unitPrice;
    private final int quantity;

    Order(Builder builder) {
        this.orderID = builder.orderID;
        this.customerID = builder.customerID;
        this.orderDate = builder.orderDate;
        this.productName = builder.productName;
        this.category = builder.category;
        this.unitPrice = builder.unitPrice;
        this.quantity = builder.quantity;
    }

    public static class Builder {

        private String orderID;
        private String customerID;
        private LocalDate orderDate;
        private String productName;
        private String category;
        private double unitPrice;
        private int quantity;

        public Builder orderID(String orderID) {
            this.orderID = orderID;
            return this;
        }

        public Builder customerID(String customerID) {
            this.customerID = customerID;
            return this;
        }

        public Builder orderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder unitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Order build() {
            return new Order(this);
        }

    }
}
