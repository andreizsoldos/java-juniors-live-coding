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
        List<Order> orders = new ArrayList<>();
        for (String line : lines) {
            try{
                String[] parts = line.split(",");

                if(parts.length != 7){
                    continue;
                }
                String orderId = parts[0];
                String productId = parts[1];
                LocalDate date = LocalDate.parse(parts[2]);
                String productName = parts[3].trim();
                String category = parts[4].trim();
                BigDecimal unitPrice = new BigDecimal(parts[5].trim());
                int quantity = Integer.parseInt(parts[6].trim());

                Order order = new Order(orderId, productId, date, productName, category, unitPrice, quantity);
                orders.add(order);
            } catch (Exception e) {

            }

        }
        return orders;
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate(),
                        Collectors.summingDouble( order -> order.getRevenue())
                ));


    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {

        return Collections.emptyList();
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
