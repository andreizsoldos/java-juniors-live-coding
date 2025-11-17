package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        List<Order> orders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(StoreAnalytics.CSV_ORDER)))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",", -1);
                if (values.length >= 3) {
                    try {
                        String orderId = line.substring(0,5);
                        String customerId = line.substring(5,10);
                        String orderDate  = String. copyValueOf(line.substring(10,19));
                        String ProductName = line.substring(1
                        String unitPrice = line.substring(19,)

                        orders.add(new Order(orderId, customerId, orderDate, ProductName, category, unitPrice, quantity ));
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format in line: " + line);
                    }
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        return  orders;
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        // Write your code here and replace the return statement
        return orders.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Order::getOrderDate,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                o -> o.getUnitPrice(),
                                BigDecimal::add
                        )
                ));

    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        // Write your code here and replace the return statement
        return orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProductName,
                        Collectors.mapping(
                                o -> o.getUnitPrice(),
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )
                ))
                .entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());
    }



    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        // Write your code here and replace the return statement
        return orders.stream()
                .filter(order -> Integer.valueOf(order.getCategory()) > minCategories)
                .toList();
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        // Write your code here and replace the return statement
        return orders.stream()
                .filter(order -> orders.contains(product))
                .findFirst();
    }
}
