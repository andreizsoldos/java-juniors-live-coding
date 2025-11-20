package com.bvd.java_fundamentals.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Avenue {
    private LocalDate orderDate;
    private BigDecimal avenue;

    public Avenue(LocalDate orderDate, BigDecimal avenue) {
        this.orderDate = orderDate;
        this.avenue = avenue;
    }

    public BigDecimal getAvenue() {
        return avenue;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}
