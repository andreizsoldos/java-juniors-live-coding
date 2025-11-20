package com.bvd.java_fundamentals;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/*
 * Implement the methods below so that the requirements are met.
 */
public class OrderUtil {

    private OrderUtil() {
    }

    // retrieve orders from csv lines
    public static List<Order> parseCsvLines(final List<String> lines) {
        return lines.stream()
                .map(s -> s.split(","))
                .filter(data -> data.length == 7)
                .map(data -> new Order
                        (
                                data[0].trim(),
                                 data[1].trim(),
                                  LocalDate.parse(data[2].trim()),
                                   data[3].trim(),
                                    data[4].trim(),
                                     new BigDecimal(data[5].trim()),
                                      Integer.parseInt(data[6].trim())
                        )

                )
                .toList();
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        Map<LocalDate, BigDecimal> totals = new HashMap<>();

        for (Order order : orders)
        {
            LocalDate date = order.getOrderDate();
            BigDecimal total = order.totalPrice();

            if (totals.containsKey(date))
            {
                totals.put(date, totals.get(date).add(total));
            } else
            {
                totals.put(date, total);
            }
        }

        List<LocalDate> sortedDates = new ArrayList<>(totals.keySet());
        sortedDates.sort(Comparator.reverseOrder());

        Map<LocalDate, BigDecimal> sorted = new LinkedHashMap<>();

        for (LocalDate date : sortedDates)
        {
            sorted.put(date, totals.get(date));
        }

        return sorted;
    }



    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
            Map<String, BigDecimal> totals = new HashMap<>();

            // Add up for each product
            for (Order order : orders)
            {
                String product = order.getProductName();
                BigDecimal total = order.totalPrice();

                totals.put(product, totals.getOrDefault(product, BigDecimal.ZERO).add(total));
            }

            // Sort products by biggest total first
            List<Map.Entry<String, BigDecimal>> sorted = new ArrayList<>(totals.entrySet());

            sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

            return sorted.subList(0,n);

    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {

        Map<String, String> customers = Map.of();
        for ( Order order : orders)
        {
            customers.putIfAbsent(order.getCustomerId(),order.getOrderId());
        }

        return (List<String>) customers;
    }

    // find the first product containing a given substring (case-insensitive)
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        if (product == null || product.isEmpty())
        {
            return Optional.empty();
        }

        String search = product.toLowerCase();

        for (Order order : orders)
        {
            if (order.getProductName().toLowerCase().contains(search))
            {
                return Optional.of(order);
            }
        }

        return Optional.empty();

    }
}
