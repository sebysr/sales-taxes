package me.buggin;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigDecimal;

/**
 * Unit tests.
 */
public class Tests
        extends TestCase {


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Tests(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static junit.framework.Test suite() {
        return new TestSuite(Tests.class);
    }

    /**
     * Tests FOOD good
     */
    public void testGoodExempt() {
        Good good1 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.FOOD_TYPE)
                .setPrice("0.85")
                .setImported(false)
                .setDescription("chocolate bar")
                .build();
        assertTrue("chocolate pretax",good1.getPricePreTaxes().equals(new BigDecimal("0.85")));
        assertTrue("chocolate postax",good1.getPriceAndTaxes().equals(new BigDecimal("0.85")));
    }

    /**
     * Tests Exempted from tax Good
     * @throws Exception
     */
    public void testAnotherGoodExempt() throws Exception {
        Good good1 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.BOOK_TYPE)
                .setPrice("12.49")
                .setImported(false)
                .setDescription("book")
                .build();
        assertTrue("book pretax",good1.getPricePreTaxes().equals(new BigDecimal("12.49")));
        assertTrue("book postax",good1.getPriceAndTaxes().equals(new BigDecimal("12.49")));
    }

    /**
     * Tests OTHER type of food, (with tax)
     */
    public void testGood() {
        Good good1 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.OTHER_TYPE)
                .setPrice("14.99")
                .setImported(false)
                .setDescription("music CD")
                .build();
        assertTrue("cd pretax",good1.getPricePreTaxes().equals(new BigDecimal("14.99")));
        assertTrue("cd postax",good1.getPriceAndTaxes().equals(new BigDecimal("16.49")));
    }

    /**
     * Test first order containing
     * 1 book : 12.49
     * 1 music CD: 14.99
     * 1 chocolate bar: 0.85
     * @throws Exception
     */
    public void testOrder1() throws Exception {

        Good good1 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.BOOK_TYPE)
                .setPrice("12.49")
                .setImported(false)
                .setDescription("book")
                .build();
        Good good2 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.OTHER_TYPE)
                .setPrice("14.99")
                .setImported(false)
                .setDescription("music CD")
                .build();
        Good good3 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.FOOD_TYPE)
                .setPrice("0.85")
                .setImported(false)
                .setDescription("chocolate bar")
                .build();

        Order order1 = OrderBuilder
                .newOrderBuilder()
                .addGood(good1)
                .addGood(good2)
                .addGood(good3)
                .build();

        System.out.println(order1);
        assertTrue("Order 1: taxes ",order1.getTotalTaxes().equals(new BigDecimal("1.50")));
        assertTrue("Order 1: total ",order1.getTotalPrice().equals(new BigDecimal("29.83")));
    }

    /**
     * Tests second order containing imported Goods
     * 1 imported box of chocolates at 10.00
     * 1 imported bottle of perfume at 47.50
     * @throws Exception
     */
    public void testOrder2() throws Exception {
        Good good1 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.FOOD_TYPE)
                .setPrice("10.00")
                .setImported(true)
                .setDescription("imported box of chocolates")
                .build();
        Good good2 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.OTHER_TYPE)
                .setPrice("47.50")
                .setImported(true)
                .setDescription("imported bottle of perfume")
                .build();


        Order order2 = OrderBuilder
                .newOrderBuilder()
                .addGood(good1)
                .addGood(good2)
                .build();

        System.out.println(order2);
        assertTrue("Order 2: taxes ",order2.getTotalTaxes().equals(new BigDecimal("7.65")));
        assertTrue("Order 2: total ",order2.getTotalPrice().equals(new BigDecimal("65.15")));
    }

    /**
     *
     * 1 imported bottle of perfume at 27.99
     * 1 bottle of perfume at 18.99
     * 1 packet of headache pills at 9.75
     * 1 box of imported chocolates at 11.25
     * @throws Exception
     */
    public void testOrder3() throws Exception {
        Good good1 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.OTHER_TYPE)
                .setPrice("27.99")
                .setImported(true)
                .setDescription("imported bottle of perfume")
                .build();
        Good good2 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.OTHER_TYPE)
                .setPrice("18.99")
                .setImported(false)
                .setDescription("bottle of perfume")
                .build();
        Good good3 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.MEDICAL_TYPE)
                .setPrice("9.75")
                .setImported(false)
                .setDescription("packet of headache pills")
                .build();
        Good good4 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.FOOD_TYPE)
                .setPrice("11.25")
                .setImported(true)
                .setDescription("box of imported chocolates")
                .build();

        Order order3 = OrderBuilder
                .newOrderBuilder()
                .addGood(good1)
                .addGood(good2)
                .addGood(good3)
                .addGood(good4)
                .build();

        System.out.println(order3);
        assertTrue("Order 3: taxes ",order3.getTotalTaxes().equals(new BigDecimal("6.70")));
        assertTrue("Order 3: total ",order3.getTotalPrice().equals(new BigDecimal("74.68")));


    }

    /**
     * Testing of the round function
     * @throws Exception
     */
    public void testRound() throws Exception {
        assertTrue(Good.round(new BigDecimal("0.03")).equals(new BigDecimal("0.05")));
        assertTrue(Good.round(new BigDecimal("0.033232")).equals(new BigDecimal("0.05")));
        assertTrue(Good.round(new BigDecimal("1.249")).equals(new BigDecimal("1.25")));
        assertTrue(Good.round(new BigDecimal("1.499")).equals(new BigDecimal("1.50")));
        assertTrue(Good.round(new BigDecimal("1.8999")).equals(new BigDecimal("1.90")));

    }
}
