package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/*
 * Implement the methods below so that the requirements are met.
 */
public class OrderUtil {

    private OrderUtil() {
    }

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) throws ParseException {
        // Write your code here and replace the return statement
        List<Order> orders = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        for (String line : lines) {
            String[] field = line.split(",");
            if (field.length < 6) {
                break;
            }
            orders.add(new Order.Builder()
                    .orderID(field[0])
                    .customerID(field[1])
                    .orderDate(LocalDate.parse(field[2]))
                    .productName(field[3])
                    .category(field[4])
                    .unitPrice(Double.parseDouble(field[5]))
                    .quantity(Integer.parseInt(field[6]))
                    .build());

        }
        return orders;
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        // Write your code here and replace the return statement
        double revenue = 0;
        Map<LocalDate, BigDecimal> map = new HashMap<>();

        for (Order order : orders) {
            if (!map.containsKey(order.getOrderDate())) {
                map.put(order.getOrderDate(), new BigDecimal(order.getQuantity() * order.getUnitPrice()));
            } else {
                map.put(order.getOrderDate(), new BigDecimal(map.get(order.getOrderDate()).doubleValue() + (order.getUnitPrice() * order.getQuantity())));
            }
        }

        return map;


    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        // Write your code here and replace the return statement

        Map<String, BigDecimal> map = new HashMap<>();

        for (Order order : orders) {
            if (!map.containsKey(order.getOrderID())) {
                map.put(order.getOrderID(), new BigDecimal(order.getUnitPrice() * order.getQuantity()));
            } else {
                map.put(order.getOrderID(), new BigDecimal(map.get(order.getOrderID()).doubleValue() + (order.getUnitPrice() * order.getQuantity())));
            }
        }


        List<Map.Entry<String, BigDecimal>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());


//        System.out.println(list.get(list.size() - 1).getKey());

        List<Map.Entry<String, BigDecimal>> result = new ArrayList<>();



        return result;
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        // Write your code here and replace the return statement
        Map<String, Integer> map = new HashMap<>();

        for (Order order : orders) {
            if (!map.containsKey(order.getCustomerID())) {
                map.put(order.getCustomerID(), 1);
            } else {
                map.put(order.getCustomerID(), map.get(order.getCustomerID()) + 1);
            }
        }

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= minCategories) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        // Write your code here and replace the return statement
        return orders.stream().filter(order -> order.getProductName().toLowerCase().contains(product.toLowerCase())).findFirst();
    }
}
