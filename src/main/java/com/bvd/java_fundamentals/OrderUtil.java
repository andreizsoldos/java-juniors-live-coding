package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/*
 * Implement the methods below so that the requirements are met.
 */
public class OrderUtil {

    private OrderUtil() {
    }

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) throws ParseException {

        List<Order> orderList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        for(String line : lines) {
            if (line.contains("BAD_LINE")) continue;
//            System.out.println(line);

//            String[] itemsCleaned = line.split(",");
            String[] itemsCleaned = line.split("\\s*,\\s*");

            Order order = new Order();
            order.setOrderId(itemsCleaned[0]);
            order.setCustomerId(itemsCleaned[1]);
            Date date = formatter.parse(itemsCleaned[2]);
            order.setOrderDate(date);
            order.setProductName(itemsCleaned[3]);
            order.setCategory(itemsCleaned[4]);
            order.setUnitPrice(Double.parseDouble(itemsCleaned[5]));
            order.setQuantity(Integer.parseInt(itemsCleaned[6]));

            orderList.add(order);
//            System.out.println(order);
        }

        return orderList;
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {

        Map<LocalDate, BigDecimal> map = new HashMap<>();

        for(Order order : orders) {
            System.out.println(order);
            BigDecimal revenue;

            revenue = BigDecimal.valueOf(order.getUnitPrice() * order.getQuantity()).setScale(2, RoundingMode.HALF_UP);;
            LocalDate date = LocalDate.ofInstant(order.getOrderDate().toInstant(), ZoneId.systemDefault());

            if (map.containsKey(date)) {
                map.put(date, map.get(date).add(revenue));
            } else {
                map.put(date, revenue);
            }

        }

        return map;
    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {

        //am vrut ceva cu stream comparator/sort si limit n. no time


        return Collections.emptyList();
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        // Write your code here and replace the return statement
        return Collections.emptyList();
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        return orders.stream().filter(order -> order.getProductName().contains(product))
                .findFirst();
    }
}
