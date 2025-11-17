package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.Order;
//import com.bvd.java_fundamentals.model.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.bvd.java_fundamentals.StoreAnalytics.CSV_ORDER;

/*
 * Implement the methods below so that the requirements are met.
 */

public class OrderUtil {

    private OrderUtil() {
    }


    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) throws IOException {
        // Write your code here and replace the return statement
        //String filePath = "C:\\Users\\dsolomon\\Documents\\java_training\\week1_ex\\java-juniors-live-coding\\src\\main\\java\\com\\bvd\\java_fundamentals\\Csv_Order.csv"; // must put csv in
        //String line;
        //String delimiter = ",";


        List<Order> orders = new ArrayList<>();

        if (lines == null || lines.isEmpty()) {
            return orders; // Return empty list if no data
        }

        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            String[] parts = line.split(",", -1); // Keep empty fields if any

            try {

                //...
                if (Order.orderDate!= null || Order.unitPrice!= null || Order.quantity!= null) {
                    orders.add(new Order(Order.orderDate, Order.unitPrice, Order.quantity));
                }

                orders.add(new Order(Order.orderDate, Order.unitPrice, Order.quantity));

            } catch (NumberFormatException e) {
                System.out.println("Skip");
            }

        }
        return orders;
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static List<Order> revenueByDay(final List<Order> orders) {
        // Write your code here and replace the return statement
        if (orders == null || orders.isEmpty()) {
            return (List<Order>) Collections.emptyMap();
        }

        return (List<Order>) orders.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Order::getOrderDate, Collectors.mapping(
                   order -> {
                       BigDecimal price = order.getUnitPrice() != null ? order.getUnitPrice() : BigDecimal.ZERO;
                       int q = Math.max(order.getQuantity(), 0);
                       return price.multiply(BigDecimal.valueOf(q));
                   },
                   Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
    }


    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        Map<LocalDate, BigDecimal> revenueMap = (Map<LocalDate, BigDecimal>) OrderUtil.revenueByDay(orders);
        revenueMap.forEach((date, revenue) -> System.out.println(date + " -> " + revenue));
        //map.keySet().stream().sorted().limit(n).collect(Collectors.toMap(Function.identity(), map::get));
        return (List<Map.Entry<String, BigDecimal>>) revenueMap;
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
