package com.bvd.java_fundamentals.mapper;

import com.bvd.java_fundamentals.model.Avenue;
import com.bvd.java_fundamentals.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderMapper {

    private OrderMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Order parseCsvLine(final String line) {
        List<String> args = List.of(line.split(","));
        try {
            return new Order(args.get(0), args.get(1),
                    LocalDate.parse(args.get(2)), args.get(3).trim(), args.get(4).trim(),
                    new BigDecimal(args.get(5)), Integer.valueOf(args.get(6)));
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            return null;
        }
    }

    public static Avenue parseAvenue(final Order order) {
        return new Avenue(order.getOrderDate(), order.getUnitPrice().multiply(new BigDecimal(order.getQuantity())));
    }
}
