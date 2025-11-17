package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Implement the methods below so that the requirements are met.
 */
public class OrderUtil {

    private OrderUtil() {}

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) {
        if (lines == null || lines.isEmpty()) return Collections.emptyList();

        return lines.stream()
                .map(line -> line.split(","))
                .filter(parts -> parts.length == 7)
                .map(parts -> {
                    try {
                        String orderId = parts[0].trim();
                        String customerId = parts[1].trim();
                        LocalDate orderDate = LocalDate.parse(parts[2].trim(), DateTimeFormatter.ISO_LOCAL_DATE);
                        String productName = parts[3].trim();
                        String category = parts[4].trim();
                        BigDecimal unitPrice = new BigDecimal(parts[5].trim());
                        int quantity = Integer.parseInt(parts[6].trim());

                        return new Order(orderId, customerId, orderDate, productName, category, unitPrice, quantity);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        if (orders == null) return Collections.emptyMap();

        Map<LocalDate, BigDecimal> revenue = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getOrderDate,
                        Collectors.reducing(BigDecimal.ZERO,
                                o -> o.getUnitPrice().multiply(BigDecimal.valueOf(o.getQuantity())),
                                BigDecimal::add)
                ));

        return revenue.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        if (orders == null || n <= 0) return Collections.emptyList();

        Map<String, BigDecimal> productRevenue = orders.stream()
                .collect(Collectors.groupingBy(
                        o -> o.getProductName().trim(),
                        Collectors.reducing(BigDecimal.ZERO,
                                o -> o.getUnitPrice().multiply(BigDecimal.valueOf(o.getQuantity())),
                                BigDecimal::add)
                ));

        return productRevenue.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        if (orders == null) return Collections.emptyList();

        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerId,
                        Collectors.mapping(Order::getCategory, Collectors.toSet())
                ))
                .entrySet().stream()
                .filter(e -> e.getValue().size() >= minCategories)
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        if (orders == null || product == null || product.isEmpty()) return Optional.empty();

        String search = product.toLowerCase();

        return orders.stream()
                .filter(o -> o.getProductName().toLowerCase().contains(search))
                .findFirst();
    }
}