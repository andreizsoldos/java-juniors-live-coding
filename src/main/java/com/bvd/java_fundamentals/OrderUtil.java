package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Implement the methods below so that the requirements are met.
 */
public class OrderUtil {

    private OrderUtil() {
    }

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) {
        List<Order> result = new ArrayList<>();

        for (String line : lines) {
            if (line == null || line.isBlank())
                continue;

            String[] parts = line.split(",");
            if (parts.length != 7)
                continue;

            try {
                String orderId = parts[0].trim();
                String customerId = parts[1].trim();
                LocalDate date = LocalDate.parse(parts[2].trim());
                String product = parts[3].trim();
                String category = parts[4].trim();
                double unitPrice = Double.parseDouble(parts[5].trim());
                int quantity = Integer.parseInt(parts[6].trim());

                result.add(new Order(orderId, customerId, date, product, category, unitPrice, quantity));
            } catch (Exception ignored) {
                break;
            }
        }
        return result;

    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getOrderDate,
                        Collectors.summingDouble(Order::getRevenue)
                ));
    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProductName,
                        Collectors.summingDouble(Order::getRevenue)
                ));
        return Collections.emptyList();
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerId,
                        Collectors.mapping(Order::getCategory, Collectors.toSet())));
    }

    // find the first product containing a given substring (case-insensitive)
    public static Stream<String> findFirstProductContaining(final List<Order> orders, final String product) {
        if (product == null)
            return Stream.empty();
        String toLower = product.toLowerCase();

        return orders.stream()
                .map(Order::getProductName)
                .filter(p -> p.toLowerCase().contains(toLower));

    }
}
