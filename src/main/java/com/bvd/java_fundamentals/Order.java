package com.bvd.java_fundamentals;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    public static LocalDate orderDate;
    public static BigDecimal unitPrice;
    public static Integer quantity;

    public Order(LocalDate orderDate, BigDecimal unitPrice, int quantity) {
        this.orderDate = orderDate;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public Order() {}

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) {this.unitPrice = unitPrice;
    }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) {
        if(quantity < 0) {
            System.out.println("Quantity cannot be negative -> setting to 0");
            this.quantity = 0;
        }else{
            this.quantity = quantity;
        }
    }


}
