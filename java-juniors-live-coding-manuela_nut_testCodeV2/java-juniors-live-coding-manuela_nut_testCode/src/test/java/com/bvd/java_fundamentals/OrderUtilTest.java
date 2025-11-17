package com.bvd.java_fundamentals;

import com.bvd.java_fundamentals.model.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.bvd.java_fundamentals.OrderUtil.customersWithCategoryDiversity;
import static com.bvd.java_fundamentals.OrderUtil.findFirstProductContaining;
import static com.bvd.java_fundamentals.OrderUtil.parseCsvLines;
import static com.bvd.java_fundamentals.OrderUtil.revenueByDay;
import static com.bvd.java_fundamentals.OrderUtil.topProductsByRevenue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderUtilTest {

    private static List<String> csvOrder;

    private static Stream<Arguments> validAndMalformed() {
        List<String> validLines = csvOrder.stream()
                .filter(line -> line.split(",").length == 7)
                .toList();
        return IntStream.range(0, validLines.size())
                .mapToObj(i -> Arguments.of(List.of((Object[]) validLines.get(i).split(",")), i));
    }

    private static Stream<Arguments> emptyAndBlankValidations() {
        return Stream.of(
                Arguments.of("empty list", List.of()),
                Arguments.of("empty string check", List.of("")),
                Arguments.of("string spaces check", List.of("    "))
        );
    }

    private static Stream<Arguments> nullAndMalformedValidations() {
        return Stream.of(
                Arguments.of("malformed check", List.of("O-001,C-001,2025-10-01")),
                Arguments.of("null check", null)
        );
    }


    @BeforeAll
    static void setUp() {
        csvOrder = StoreAnalytics.CSV_ORDER;
    }

    @ParameterizedTest(name = "Parsing line: {0}")
    @MethodSource("validAndMalformed")
    void testParseCsvLines_validAndMalformed(final List<?> expected, final int index) {
        List<Order> orders = parseCsvLines(csvOrder);
        assertEquals(expected.get(0), orders.get(index).getOrderId());
        assertEquals(expected.get(1), orders.get(index).getCustomerId());
        assertEquals(LocalDate.parse(((String) expected.get(2)).trim()), orders.get(index).getOrderDate());
        assertEquals(((String) expected.get(3)).trim(), orders.get(index).getProductName());
        assertEquals(expected.get(4), orders.get(index).getCategory());
        assertEquals(new BigDecimal((String) expected.get(5)), orders.get(index).getUnitPrice());
        assertEquals(Integer.parseInt((String) expected.get(6)), orders.get(index).getQuantity());
    }

    @ParameterizedTest(name = "Parsing line: {0}")
    @MethodSource("emptyAndBlankValidations")
    void testInputValidation(final String description, final List<String> expected) {
        List<Order> orders = parseCsvLines(expected);
        assertTrue(orders.isEmpty());
    }

    @ParameterizedTest(name = "Parsing line: {0}")
    @MethodSource("nullAndMalformedValidations")
    void testMalformedValidation(final String description, final List<String> expected) {
        List<Order> orders = parseCsvLines(expected);
        assertTrue(orders.isEmpty());
    }

    @Test
    void testParseCsvLines_doesNotThrowError() {
        assertDoesNotThrow(() -> parseCsvLines(csvOrder));
    }

    @Test
    void testRevenueByDay() {
        List<Order> orders = parseCsvLines(csvOrder);
        Map<LocalDate, BigDecimal> revenue = revenueByDay(orders);
        assertEquals(new BigDecimal("44.48"), revenue.get(LocalDate.of(2025, 10, 1)));
        assertEquals(new BigDecimal("96.00"), revenue.get(LocalDate.of(2025, 10, 2)));
        assertEquals(new BigDecimal("1507.99"), revenue.get(LocalDate.of(2025, 10, 3)));
        assertEquals(new BigDecimal("379.98"), revenue.get(LocalDate.of(2025, 10, 4)));
    }

    @Test
    void testTopProductsByRevenue() {
        List<Order> orders = parseCsvLines(csvOrder);
        var top3 = topProductsByRevenue(orders, 3);
        assertEquals("Notebook 15", top3.get(0).getKey());
        assertEquals(new BigDecimal("799.00"), top3.get(0).getValue());
        assertEquals("Notebook 13", top3.get(1).getKey());
        assertEquals(new BigDecimal("699.00"), top3.get(1).getValue());
        assertEquals("Monitor 27", top3.get(2).getKey());
        assertEquals(new BigDecimal("379.98"), top3.get(2).getValue());
    }

    @Test
    void testCustomersWithCategoryDiversity() {
        List<Order> orders = parseCsvLines(csvOrder);
        var diverseCustomers = customersWithCategoryDiversity(orders, 2);
        assertTrue(diverseCustomers.contains("C-002"));
        assertFalse(diverseCustomers.contains("C-001"));
        assertFalse(diverseCustomers.contains("C-003"));
        assertFalse(diverseCustomers.contains("C-004"));
    }

    @Test
    void testFindFirstProductContaining_caseInsensitive() {
        List<Order> orders = parseCsvLines(csvOrder);
        var found = findFirstProductContaining(orders, "USB");
        assertTrue(found.isPresent());
        assertEquals("USB-C Cable", found.get().getProductName().trim());
    }
}