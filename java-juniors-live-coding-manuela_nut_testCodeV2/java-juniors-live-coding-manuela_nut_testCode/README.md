# java-juniors-live-coding
Live coding

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
          
