package com.bvd.java_fundamentals.Work;
/*

Each element (considered to be a line in CSV) has the following format: orderId,customerId,orderDate,productName,
category,unitPrice,quantity

orderDate is ISO date yyyy-MM-dd
unitPrice is decimal (e.g., 19.99)
quantity is an integer
Tasks:

Implement Order class (decide list of fields and methods based on the tasks requirements)
*/

import java.time.LocalDate;

public class Order {

    private final String orderId;
    private final String customerId;
    private final LocalDate orderDate;
    private final String productName;
    private final String category;
    private final double unitPrice;
    private final int quantity;


    public Order(String orderId, String customerId, LocalDate orderDate,
                 String productName, String category, double unitPrice, int quantity) {
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public double revenue() {
        return unitPrice * quantity;
    }



}
