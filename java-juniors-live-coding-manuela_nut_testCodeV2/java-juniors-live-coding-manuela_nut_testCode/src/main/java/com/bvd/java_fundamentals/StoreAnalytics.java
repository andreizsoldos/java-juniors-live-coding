package com.bvd.java_fundamentals;

import java.util.List;

import static com.bvd.java_fundamentals.OrderUtil.customersWithCategoryDiversity;
import static com.bvd.java_fundamentals.OrderUtil.findFirstProductContaining;
import static com.bvd.java_fundamentals.OrderUtil.parseCsvLines;
import static com.bvd.java_fundamentals.OrderUtil.revenueByDay;
import static com.bvd.java_fundamentals.OrderUtil.topProductsByRevenue;

/*
 * You receive a raw CSV order data from a tiny online shop. You need to parse it and compute a few analytics.
 * The focus is collections, streams, String handling, OO basics, and edge cases.

 * The CSV order data is given in main method, as a list collection just to not complicate more the exercise.
 * Each element (considered to be a line in CSV) has the following format:
        orderId,customerId,orderDate,productName,category,unitPrice,quantity
            - orderDate is ISO date yyyy-MM-dd
            - unitPrice is decimal (e.g., 19.99)
            - quantity is an integer

 * Tasks:
    - Implement Order class (decide list of fields and methods based on the tasks requirements)
    - Implement business logic for the methods from OrderUtil utility class so that all tests are passing
        * parseCsvLines: parse the raw CSV lines into a list of Order objects, skipping malformed lines
        * revenueByDay: calculate total revenue per day (revenue = unitPrice * quantity)
        * topProductsByRevenue: get top "n" products by total revenue
        * customersWithCategoryDiversity: get customers who ordered products from at least 2 categories
        * findFirstProductContaining: find the first product name containing a given substring (case-insensitive)
 */
public class StoreAnalytics {
    public static final List<String> CSV_ORDER = List.of(
            "O-1001,C-001,2025-10-01, USB-C Cable ,Accessories,9.99,2",
            "O-1002,C-002,2025-10-01,Wireless Mouse,Accessories,24.50,1",
            "O-1003,C-001,2025-10-02,Laptop Sleeve,Accessories,17.00,1",
            "O-1004,C-003,2025-10-02,Mechanical Keyboard,Accessories,79.00,1",
            "O-1005,C-002,2025-10-03,Notebook 15,Computers,799.00,1",
            "O-1006,C-004,2025-10-03,Notebook 13,Computers,699.00,1",
            "O-1007,C-001,2025-10-03, USB-c cable,Accessories,9.99,1",
            "BAD_LINE_THAT_SHOULD_BE_SKIPPED",
            "O-1008,C-002,2025-10-04,Monitor 27,Displays,189.99,2"
    );

    /* The expected output of the main method is:
        Valid orders: 8
        Revenue by day: {2025-10-04=379.98, 2025-10-03=1507.99, 2025-10-02=96.00, 2025-10-01=44.48}
        Top 3 products: [Notebook 15=799.00, Notebook 13=699.00, Monitor 27=379.98]
        Customers who ordered from different categories (>=2 categories): [C-002]
        First containing 'USB': Optional[Order(orderId=O-1001, customerId=C-001, orderDate=2025-10-01,
        productName=USB-C Cable, category=Accessories, unitPrice=9.99, quantity=2)]
     */
    public static void main(String[] args) {
        final var orders = parseCsvLines(CSV_ORDER);
        System.out.println("Valid orders: %s".formatted(orders.size()));
        System.out.println("Revenue by day: %s".formatted(revenueByDay(orders)));
        System.out.println("Top 3 products: %s".formatted(topProductsByRevenue(orders, 3)));
        System.out.println("Customers who ordered from different categories (>=2 categories): %s".formatted(customersWithCategoryDiversity(orders, 2)));
        System.out.println("First containing 'USB': %s".formatted(findFirstProductContaining(orders, "USB")));
    }
}
