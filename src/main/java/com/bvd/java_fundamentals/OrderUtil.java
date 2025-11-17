package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Implement the methods below so that the requirements are met.
 */
public class OrderUtil {

    private OrderUtil() {
    }

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) {
        List<String> goodLines = lines.stream()
                .filter(x -> x.startsWith("0-"))
                .toList();

        List<Order> result = new ArrayList<>();

        goodLines.forEach(line -> {
            Order newOrder = new Order(....);     // each line to Order Object
            result.add(newOrder);
        });


        return Collections.emptyList();
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {

        Map<LocalDate, BigDecimal> result = orders.stream()
                .collect(Collectors.groupingBy(x-> x.getOrderDate(),  unitPrice * quantity ));

        return result;
    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {

        List<Map.Entry<String, BigDecimal>> result = new ArrayList<>();
        orders.stream().sorted(x -> x.getQuantity() * x.getQuantity())

// sort by total revenue , first n

        return result;
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        // Write your code here and replace the return statement
        return Collections.emptyList();
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        // Write your code here and replace the return statement
        return Optional.empty();
    }
}
