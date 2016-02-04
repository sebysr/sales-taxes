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
     * Tests scanning method for order
     * with Dependency Injection
     * @throws Exception
     */
    public void testOrderScanned() throws Exception {
        String input = "1 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85";

        TypeInjector injector = new MapInjector();

        Cart cart = injector.getParser().parse(input);
        System.out.println(cart);
        assertTrue(cart.getTotalPrice().equals(new BigDecimal("29.83")));
        assertTrue(cart.getTotalTaxes().equals(new BigDecimal("1.50")));
    }

    /**
     * Tests FOOD good
     */
    public void testGoodExempt() {
        Good good1 = GoodBuilder
                .newGoodBuilder()
                .howMany(1)
                .ofType(GoodType.FOOD_TYPE)
                .withPrice("0.85")
                .isImported(false)
                .withDescription("chocolate bar")
                .build();
        assertTrue("chocolate pretax", good1.getPriceBeforeTaxes().equals(new BigDecimal("0.85")));
        assertTrue("chocolate postax", good1.getPriceAfterTaxes().equals(new BigDecimal("0.85")));
    }

    /**
     * Tests Exempted from tax Good
     *
     * @throws Exception
     */
    public void testAnotherGoodExempt() throws Exception {
        Good good1 = GoodBuilder
                .newGoodBuilder()
                .howMany(1)
                .ofType(GoodType.BOOK_TYPE)
                .withPrice("12.49")
                .isImported(false)
                .withDescription("book")
                .build();
        assertTrue("book pretax", good1.getPriceBeforeTaxes().equals(new BigDecimal("12.49")));
        assertTrue("book postax", good1.getPriceAfterTaxes().equals(new BigDecimal("12.49")));
    }

    /**
     * Tests OTHER type of food, (with tax)
     */
    public void testGood() {
        Good good1 = GoodBuilder
                .newGoodBuilder()
                .howMany(1)
                .ofType(GoodType.OTHER_TYPE)
                .withPrice("14.99")
                .isImported(false)
                .withDescription("music CD")
                .build();
        assertTrue("cd pretax", good1.getPriceBeforeTaxes().equals(new BigDecimal("14.99")));
        assertTrue("cd postax", good1.getPriceAfterTaxes().equals(new BigDecimal("16.49")));
    }

    /**
     * Test first order containing
     * 1 book : 12.49
     * 1 music CD: 14.99
     * 1 chocolate bar: 0.85
     *
     * @throws Exception
     */
    public void testOrder1() throws Exception {
        GoodBuilder aProduct = GoodBuilder.newGoodBuilder();
        Good good1 = aProduct
                .howMany(1)
                .ofType(GoodType.BOOK_TYPE)
                .withPrice("12.49")
                .isImported(false)
                .withDescription("book")
                .build();
        Good good2 = aProduct
                .howMany(1)
                .ofType(GoodType.OTHER_TYPE)
                .withPrice("14.99")
                .isImported(false)
                .withDescription("music CD")
                .build();
        Good good3 = aProduct
                .howMany(1)
                .ofType(GoodType.FOOD_TYPE)
                .withPrice("0.85")
                .isImported(false)
                .withDescription("chocolate bar")
                .build();

        Cart cart1 = CartBuilder
                .newOrderBuilder()
                .addGood(good1)
                .addGood(good2)
                .addGood(good3)
                .build();

        System.out.println(cart1);
        assertTrue("Cart 1: taxes ", cart1.getTotalTaxes().equals(new BigDecimal("1.50")));
        assertTrue("Cart 1: total ", cart1.getTotalPrice().equals(new BigDecimal("29.83")));
    }

    /**
     * Tests second order containing imported Goods
     * 1 imported box of chocolates at 10.00
     * 1 imported bottle of perfume at 47.50
     *
     * @throws Exception
     */
    public void testOrder2() throws Exception {
        GoodBuilder aProduct = GoodBuilder.newGoodBuilder();

        Good good1 = aProduct
                .ofType(GoodType.FOOD_TYPE)
                .withPrice("10.00")
                .withDescription("imported box of chocolates")
                .howMany(1)
                .isImported(true)
                .build();
        Good good2 = aProduct
                .howMany(1)
                .ofType(GoodType.OTHER_TYPE)
                .withPrice("47.50")
                .isImported(true)
                .withDescription("imported bottle of perfume")
                .build();


        CartBuilder cart = CartBuilder.newOrderBuilder();
        Cart cart2 = cart
                .addGood(good1)
                .addGood(good2)
                .build();

        System.out.println(cart2);
        assertTrue("Cart 2: taxes ", cart2.getTotalTaxes().equals(new BigDecimal("7.65")));
        assertTrue("Cart 2: total ", cart2.getTotalPrice().equals(new BigDecimal("65.15")));
    }



    /**
     * 1 imported bottle of perfume at 27.99
     * 1 bottle of perfume at 18.99
     * 1 packet of headache pills at 9.75
     * 1 box of imported chocolates at 11.25
     *
     * @throws Exception
     */
    public void testOrder3() throws Exception {
        GoodBuilder aProduct = GoodBuilder.newGoodBuilder();
        CartBuilder myCart = CartBuilder.newOrderBuilder();

        Good good1 = aProduct
                .howMany(1)
                .ofType(GoodType.OTHER_TYPE)
                .withPrice("27.99")
                .isImported(true)
                .withDescription("imported bottle of perfume")
                .build();
        Good good2 = aProduct
                .howMany(1)
                .ofType(GoodType.OTHER_TYPE)
                .withPrice("18.99")
                .isImported(false)
                .withDescription("bottle of perfume")
                .build();
        Good good3 = aProduct
                .howMany(1)
                .ofType(GoodType.MEDICAL_TYPE)
                .withPrice("9.75")
                .isImported(false)
                .withDescription("packet of headache pills")
                .build();
        Good good4 = aProduct
                .howMany(1)
                .ofType(GoodType.FOOD_TYPE)
                .withPrice("11.25")
                .isImported(true)
                .withDescription("box of imported chocolates")
                .build();

        Cart cart3 = myCart
                .addGood(good1)
                .addGood(good2)
                .addGood(good3)
                .addGood(good4)
                .build();

        System.out.println(cart3);
        assertTrue("Cart 3: taxes ", cart3.getTotalTaxes().equals(new BigDecimal("6.70")));
        assertTrue("Cart 3: total ", cart3.getTotalPrice().equals(new BigDecimal("74.68")));


    }

    /**
     * tests that total taxes for medical type are zero with deep injection pattern
     * @throws Exception
     */
    public void testDepInj() throws Exception {
        String input = "1 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85";

        TypeInjector injector = new DummyInjector();

        Cart cart = injector.getParser().parse(input);
        System.out.println( cart.toVerboseString());

        assertTrue(cart.getTotalTaxes().equals(new BigDecimal("0.00")));

    }

    /**
     * Testing of the round function
     *
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
