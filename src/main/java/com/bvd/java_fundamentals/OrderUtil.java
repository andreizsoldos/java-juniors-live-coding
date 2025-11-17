package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/*
 * Implement the methods below so that the requirements are met.
 */
public class OrderUtil {

    private OrderUtil() {
    }

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) {
        List<Order> orders = new ArrayList<>();
        if (lines == null) return orders;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (String line : lines) {
            if (line == null || line.isBlank()) continue;

            String[] parts = line.split(",", -1);

            if (parts.length != 7) {
                System.out.println("Skipping invalid line: " + line);
                continue;
            }

            try {
                String orderId     = parts[0].trim();
                String customerId  = parts[1].trim();
                Date orderDate   = Date.parse(parts[2].trim()); // m-am blocat aici, inital am folosit String, dar am zis sa schimb in date

                String productName = parts[3].trim();
                String category    = parts[4].trim();
                double unitPrice   = Double.parseDouble(parts[5].trim());
                int quantity       = Integer.parseInt(parts[6].trim());
                double revenue     = unitPrice * quantity;

                orders.add(new Order(orderId, customerId, orderDate, productName, category,
                        unitPrice, quantity, revenue));
            } catch (Exception e) {
                System.out.println("Error parsing line: " + line);
            }
        }
        return orders;
    }


    // calculate revenue by day
        // revenue = unitPrice * quantity
        public static Map<Date, BigDecimal> revenueByDay (final List<Order> orders){

            Map<Date, BigDecimal> revenueMap = orders.stream()
                    .collect(Collectors.groupingBy(
                            Order::getOrderDate,
                            Collectors.reducing(
                                    BigDecimal.ZERO,
                                    order -> BigDecimal.valueOf(order.getUnitPrice())
                                            .multiply(BigDecimal.valueOf(order.getQuantity())),
                                    BigDecimal::add
                            )
                    ));
            return  revenueMap;
        }

        // get top "n" products by revenue
        public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
            Map<String, BigDecimal> revenueByProduct = orders.stream()
                    .collect(Collectors.groupingBy(
                            Order::getProductName,
                            Collectors.reducing(
                                    BigDecimal.ZERO,
                                    o -> BigDecimal.valueOf(o.getUnitPrice())
                                            .multiply(BigDecimal.valueOf(o.getQuantity())),
                                    BigDecimal::add
                            )
                    ));

            return revenueByProduct.entrySet().stream()
                    .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                    .limit(Math.max(0, n))
                    .collect(Collectors.toList());
        }


    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        Map<String, Set<String>> categoriesByCustomer = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerId,
                        Collectors.mapping(Order::getCategory, Collectors.toSet())
                ));

        return categoriesByCustomer.entrySet().stream()
                .filter(e -> e.getValue().size() >= Math.max(0, minCategories))
                .map(Map.Entry::getKey)
                .sorted()
                .collect(Collectors.toList());
    }


    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        if (product == null || product.isBlank()) {return Optional.empty();}
        final String searchTerm = product.toLowerCase();

        return orders.stream()
                .filter(o -> o.getProductName() != null && o.getProductName().toLowerCase().contains(searchTerm))
                .findFirst();
    }


}