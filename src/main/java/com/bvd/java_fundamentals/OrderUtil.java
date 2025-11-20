package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.mapper.OrderMapper;
import com.bvd.java_fundamentals.model.Avenue;
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
        throw new IllegalArgumentException("Utility class");
    }

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) {
        if (lines == null || lines.isEmpty()) {
            return new ArrayList<>();
        }
        return lines.stream()
                .map(OrderMapper::parseCsvLine).filter(Objects::nonNull).toList();
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return new HashMap<>();
        }
        return orders.stream()
                .map(OrderMapper::parseAvenue)
                .collect(Collectors.toMap(Avenue::getOrderDate, Avenue::getAvenue));
    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        if (orders == null || orders.isEmpty()) {
            return new ArrayList<>();
        }
        return revenueByDay(orders).values().stream().sorted().limit(n);
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        if (orders == null || orders.isEmpty()) {
            return new ArrayList<>();
        }
        // Write your code here and replace the return statement
        return Collections.emptyList();
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        if (orders == null || orders.isEmpty()) {
            return Optional.empty();
        }
        // Write your code here and replace the return statement
        return Optional.empty();
    }
}
