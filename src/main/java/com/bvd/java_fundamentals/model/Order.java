package com.bvd.java_fundamentals.model;

import java.time.LocalDate;

public class Order {

    private Integer orderId;
    private Integer customerId;
    private LocalDate orderDate;
    private String category;
    private String productName;
    private Double unitPrice;
    private Integer quantity;


    public Order(Integer orderId, Integer quantity, Double unitPrice, String category, String productName, LocalDate orderDate, Integer customerId) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.category = category;
        this.orderDate = orderDate;
        this.customerId = customerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }



    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
