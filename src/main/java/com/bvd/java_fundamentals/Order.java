package com.bvd.java_fundamentals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    private String orderId;
    private String customerId;
    private LocalDate orderDate;
    private String productName;
    private String category;
    private Double unitPrice;
    private Integer quantity;

}
