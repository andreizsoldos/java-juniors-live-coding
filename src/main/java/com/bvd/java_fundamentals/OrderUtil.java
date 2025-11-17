package com.bvd.java_fundamentals;


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


    public static List<Order> parseCsvLines(final List<String> lines) {
        if (lines == null || lines.isEmpty()) return List.of();

        final List<Order> result = new ArrayList<>();
        for (String raw : lines) {
            if (raw == null) continue;
            final String line = raw.trim();
            if (line.isEmpty()) continue;


            final String[] parts = line.split(",", -1);
            if (parts.length != 7) {

                continue;
            }
            try {

                final String orderId = parts[0].trim();
                final String customerId = parts[1].trim();
                final LocalDate date = LocalDate.parse(parts[2].trim());
                final String productName = parts[3].trim();
                final String category = parts[4].trim();
                final BigDecimal unitPrice = new BigDecimal(parts[5].trim());
                final int quantity = Integer.parseInt(parts[6].trim());


                if (orderId.isEmpty() || customerId.isEmpty() || productName.isEmpty() || category.isEmpty() || quantity < 0) {
                    continue;
                }

                result.add(new Order(orderId, customerId, date, productName, category, unitPrice, quantity));
            } catch (Exception ignored) {

            }
        }
        return result;
    }


    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        if (orders == null || orders.isEmpty()) return Map.of();

        return orders.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        Order::getOrderDate,
                        Order::getRevenue,
                        BigDecimal::add,
                        LinkedHashMap::new))

                .entrySet().stream()
                .sorted(Map.Entry.<LocalDate, BigDecimal>comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }


    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        if (orders == null || orders.isEmpty() || n <= 0) return Collections.emptyList();

        Map<String, BigDecimal> productRevenue = orders.stream()
                .collect(Collectors.groupingBy(Order::getProductName, Collectors.reducing(BigDecimal.ZERO,
                        Order::getRevenue, BigDecimal::add)));

        return productRevenue.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .collect(Collectors.toList());
    }


    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        // Write your code here and replace the return statement
        return Collections.emptyList();
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        if (orders == null || orders.isEmpty() | product == null || product.isBlank()) {
            return Optional.empty();

        }
        final String lowerCaseProduct = product.trim().toLowerCase();
        return orders.stream()
                .filter(order -> order.getProductName().trim().toLowerCase().contains(lowerCaseProduct))
                .findFirst();

    }
}