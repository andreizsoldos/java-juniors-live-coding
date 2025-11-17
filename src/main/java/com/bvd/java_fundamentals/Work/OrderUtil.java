package com.bvd.java_fundamentals.Work;
import com.bvd.java_fundamentals.model.Order;

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
        /*
        Initial am incercat sa adaptez acesta metoda,
        List<List<String>> records = new ArrayList<>();
try (BufferedReader br = new BufferedReader(new FileReader("book.csv"))) {
    String line;
    while ((line = br.readLine()) != null) {
        String[] values = line.split(COMMA_DELIMITER);
        records.add(Arrays.asList(values));
    }
}
        dar am realizat dupa ca am doar 7 elemente si ca as putea
        face citirea dupa ele, doar ca nu imi pot da seama pe moment ce nu ii convine la model, adica nu ma lasta intelij sa instalez
        acea dependinta
         */


        List<Order> result = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(" , ");

            if (parts.length != 7) {
                break;
            }
            try {
                String orderId = parts[0];
                String customerId = parts[1];
                LocalDate date = LocalDate.parse(parts[2]);
                String productName = parts[3].trim();
                String category = parts[4];
                double unitPrice = Double.parseDouble(parts[5]);
                int quantity = Integer.parseInt(parts[6]);
                result.add(new Order(orderId, customerId, date, productName, category, unitPrice, quantity));
            } catch (Exception ignored) {

            }
        return result;
    }
    }

    // calculate revenue by day
    // revenue = unitPrice * quantity
    //Aici am incercat sa fac calculul dar sincer.. cum nu am putut testa nu am idee daca am gresit ceva, sper
    //ca logica macar sa fie ok
    public static Map<LocalDate, BigDecimal> revenueByDay(final List<Order> orders) {
        Map<LocalDate, Double> revenueByDay(List<Order> orders) {
            return orders.stream()
                    .collect(Collectors.groupingBy(
                            Order::getOrderDate,
                            Collectors.summingDouble(Order::revenue),
                            TreeMap::new

                    ))

        }

    }

    // get top "n" products by revenue
    public static List<Map.Entry<String, BigDecimal>> topProductsByRevenue(final List<Order> orders, final int n) {
        // Write your code here and replace the return statement
        return Collections.emptyList();
    }

    // get customers who ordered products from at least "minCategories" different categories
    public static List<String> customersWithCategoryDiversity(final List<Order> orders, final int minCategories) {
        // Write your code here and replace the return statement
        return Collections.emptyList();
    }

    // find the first product containing a given substring (case-insensitive)
    //aici am facut filtrarea, din fericire am mai facut ceva asemanator anterior
    public static Optional<Order> findFirstProductContaining(final List<Order> orders, final String product) {
        String word = substring.toLowerCase();

        return orders.stream()
                .filter(o -> o.getProductName().toLowerCase().contains(word))
                .findFirst();
    }
}
