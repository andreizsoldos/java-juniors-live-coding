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

        List<Order> result = new ArrayList<>();

        if (lines == null) {
            return result;
        }

        for (String row :
                lines) {
            if (row == null) {
                continue;  // daca e gol skip la next , check better if the line is malformed
            }
            if (!(row.startsWith("O-"))) {   // de adaugat o verificare mai buna??
                continue;
            }

            String[] splitRow = row.split(",");

            if (splitRow.length < 7) {    // daca are sub 7 coloane --> malformed
                continue;
            }

            System.out.println(Arrays.toString(splitRow));
            Order order = new Order(
                    splitRow[0].trim(),
                    splitRow[1].trim(),
                    LocalDate.parse(splitRow[2]),
                    splitRow[3].trim(),
                    splitRow[4].trim(),
                    new BigDecimal(splitRow[5]),
                    Integer.parseInt(splitRow[6])
            );

            result.add(order);
        }

        return result;
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {

        Map<LocalDate, BigDecimal> result = new HashMap<>();

        if (orders == null) {
            return result;
        }

        result = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getOrderDate,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                order -> order.getUnitPrice()
                                        .multiply(BigDecimal.valueOf(order.getQuantity())),
                                BigDecimal::add
                        )
                ));

        System.out.println("ex2----------------------------" + result.entrySet());

        return result;
    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {

        List<Map.Entry<String, BigDecimal>> result = new ArrayList<>();

        if (orders == null) {
            return result;
        }
        if (n < 1) {
            return result;
        }

        Map<String, BigDecimal> revenuePerProd = new HashMap<>();

        revenuePerProd = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProductName,
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                order -> order.getUnitPrice()
                                        .multiply(BigDecimal.valueOf(order.getQuantity())),
                                BigDecimal::add
                        )
                ));
        System.out.println("ex3-----------------------------------" + revenuePerProd);

        result = revenuePerProd.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(n)
                .collect(Collectors.toList());

        return result;
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {

        List<String> result = new ArrayList<>();

        if (orders == null) {
            return result;
        }

        Map<String, Set<String>> intermediatMap = orders.stream()
                .collect(Collectors.groupingBy(
                                Order::getCustomerId,
                                Collectors.mapping(Order::getCategory, Collectors.toSet())
                        )
                );

        System.out.println("ex 4----------------------------------------" + intermediatMap);

        result = intermediatMap.entrySet().stream()
                .filter(x -> x.getValue().size() >= minCategories)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("ex 4----------------------------------------" + result);

        return result;
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {

        if (orders == null) {
            return Optional.empty();
        }
        if (product == null) {
            return Optional.empty();
        }

        Optional<Order> op = orders.stream()
                .filter(x -> x.getProductName().contains(product))
                .findFirst();

        System.out.println("ex 5 ----------------------------------" + op);

        return op;
    }
}
