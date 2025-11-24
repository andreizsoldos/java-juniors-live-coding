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
        // Write your code here and replace the return statement
        if (lines == null || lines.size() == 0) {
            return Collections.emptyList();
        }
        // orderId,customerId,orderDate,productName,category,unitPrice,quantity
        //"O-1001,C-001,2025-10-01, USB-C Cable ,Accessories,9.99,2",
        return lines.stream()
                .map(l -> l.split(","))
                .filter(l -> l.length == 7)
                .map(arr ->
                        new Order(
                                arr[0].trim(),
                                arr[1].trim(),
                                LocalDate.parse(arr[2].trim()),
                                arr[3].trim(),
                                arr[4].trim(),
                                new BigDecimal(arr[5].trim()),
                                Integer.parseInt(arr[6].trim())
                        ))
                .collect(Collectors.toList());

    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        // Write your code here and replace the return statement
        if (orders == null || orders.size() == 0) {
            return Collections.emptyMap();
        }

        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getOrderDate,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                (Order o) -> o.getUnitPrice().multiply(new BigDecimal(o.getQuantity())),
                                BigDecimal::add
                        )
                ));


    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        // Write your code here and replace the return statement
        //return Collections.emptyList();
        if (orders == null || orders.size() == 0) {
            return Collections.emptyList();
        }
        var list = new ArrayList<Map.Entry<String, BigDecimal>>();

        Map<String, BigDecimal> revenueByProducts = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProductName,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                (Order o) -> o.getUnitPrice().multiply(new BigDecimal(o.getQuantity())),
                                BigDecimal::add
                        )
                ));
        revenueByProducts.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(n)
                .forEach(list::add);

        return list;

    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        // Write your code here and replace the return statement
        //return Collections.emptyList();
        if (orders == null || orders.size() == 0) {
            return Collections.emptyList();
        }

        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getCustomerId,
                        Collectors.mapping(Order::getCategory, Collectors.toSet())
                )).entrySet().stream()
                .filter(val -> val.getValue().size() >= minCategories)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        // Write your code here and replace the return statement
        //return Optional.empty();
        if (orders == null || orders.size() == 0) {
            return Optional.empty();
        }
        return orders.stream()
                .filter(order -> order.getProductName().toLowerCase().contains(product.toLowerCase()))
                .findFirst();
    }
}