package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Implement the methods below so that the requirements are met.
 */
public class OrderUtil {

    private Order order;

    private OrderUtil() {
    }

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) {
        List<Order> orders = new ArrayList<>();
        for(String line : lines) {
            String[] fields = line.split(",");
            if (fields.length > 6) {
                Order order = new Order();
                order.setOrderId(fields[0]);
                order.setCustomerId(fields[1]);
                order.setOrderDate(LocalDate.parse(fields[2]));
                order.setProductName(fields[3]);
                order.setCategory(fields[4]);
                order.setUnitPrice(Double.parseDouble(fields[5]));
                order.setQuantity(Integer.parseInt(fields[6]));
                orders.add(order);
            }

        }
        return orders;
    }




    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {

        return orders.stream().collect(Collectors.groupingBy(o -> o.getOrderDate(), Collectors.summingDouble(o -> BigDecimal.valueOf(o.getQuantity() * o.getUnitPrice())) ));


    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {

        Map<String, Order> filtered =  orders.stream().collect()

    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        return orders.stream().collect(Collectors.groupingBy(o -> o.getCustomerId()));

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        // Write your code here and replace the return statement
        return Optional.empty();
    }
}
