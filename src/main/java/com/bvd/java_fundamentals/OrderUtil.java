package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
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
        List<Order> orders = lines.stream().map(a -> {
            if(a.contains(",")) {
                Order order = new Order();
                order.setOrderId(Integer.parseInt(a.split(",")[0]));
                order.setCustomerId(Integer.parseInt(a.split(",")[1]));
                order.setOrderDate(LocalDate.parse(a.split(",")[2]));
                order.setProductName(a.split(",")[3]);
                order.setCategory(a.split(",")[4]);
                order.setUnitPrice(Double.parseDouble(a.split(",")[5]));
                order.setQuantity(Integer.parseInt(a.split(",")[6]));
                return order;
            }
            return null;
        }).toList();
        return Collections.emptyList();
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        // Write your code here and replace the return statement
        Map<LocalDate, BigDecimal> map = orders.stream().collect(Collectors.toMap())
        return map;
    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        // Write your code here and replace the return statement
        return Collections.emptyList();
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        List<Order> customers = orders.stream().filter()
        return Collections.emptyList();
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        for(Order order : orders) {
            if(order.getProductName().toLowerCase().contains(product.toLowerCase()) ||
                    order.getCategory().toLowerCase().contains(product.toLowerCase())) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }
}
