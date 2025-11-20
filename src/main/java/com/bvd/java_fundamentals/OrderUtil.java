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
        if (lines == null)
            return Collections.emptyList();
        return lines.stream()
                .map(field -> field.split(","))
                .filter(s -> s.length == 7)
                .map(ord -> new Order(
                        ord[0].trim(),
                        ord[1].trim(),
                        LocalDate.parse(ord[2]),
                        ord[3].trim(),
                        ord[4].trim(),
                        new BigDecimal(ord[5]),
                        Integer.parseInt(ord[6])
                ))
                .toList();
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getOrderDate,
                        Collectors.reducing(BigDecimal.ZERO,
                                Order::getRevenue,
                                BigDecimal::add)
                ));

    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        Map<String, BigDecimal> productsWithRevenue = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProductName,
                        Collectors.mapping(Order::getRevenue, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ));
                return productsWithRevenue.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .toList();
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        Map<String, Long> productsWithRevenue = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerId,
                        Collectors.counting()
                        )
                );

        // In loc de counting vreau sa fac un set cu categoriile si dupa dadeam count la el sa vad cate categorii au si
        // si daca aveau min filtram acel set intr o lista si o returnam
        List<String> customers= productsWithRevenue.entrySet()
                .stream().filter(e -> e.getValue() > minCategories)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("productsWithRevenue: " + customers);
        return customers;
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        return orders.stream()
                .filter(order -> order.getProductName().toLowerCase().contains(product.toLowerCase()))
                .findFirst();
    }
}
