package me.buggin;

import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigDecimal;

import static me.buggin.ProductType.*;

/**
 * Unit tests.
 */
public class Tests
        extends TestCase {

    private TypeInjector injector;

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

    public void setUp() throws Exception {
        injector = new TypeInjector() {
            @Override
            public TypeRequester getParser() {
                return new Parser(new TypeProvider() {
                    @Override
                    public ProductType getType(String key) {
                        return MEDICAL;
                    }
                });
            }
        };
    }

    /**
     * old plain test dependency injection
     * @throws Exception
     */
    public void testDummyInjector() throws Exception {
        String input = "1 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85";
        Cart cart = injector.getParser().parse(input);
        System.out.println(cart);

        assertTrue("Cart 1: price ", cart.getTotalPrice().equals(new BigDecimal("28.33")));
        assertTrue("Cart 1: taxes ", cart.getTotalTaxes().equals(new BigDecimal("0.00")));

    }

    public void tearDown() throws Exception {
        injector = null;
    }

    /**
     * Tests scanning method for order
     * with Guice Dependency Injection
     *
     * @throws Exception
     */
    public void testOrderScanned() throws Exception {
        String input = "1 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85";

        Injector injector = Guice.createInjector(new MapInjector());
        Parser parser = injector.getInstance(Parser.class);

        Cart cart = parser.parse(input);
        System.out.println(cart);

        input = "1 imported box of chocolates at 10.00\n" +
                "1 imported bottle of perfume at 47.50";
        Cart cart2 = parser.parse(input);
        System.out.println(cart2);

        input = "1 imported bottle of perfume at 27.99\n" +
                "1 bottle of perfume at 18.99\n" +
                "1 packet of headache pills at 9.75\n" +
                "1 box of imported chocolates at 11.25";
        Cart cart3 = parser.parse(input);
        System.out.println(cart3);

        assertTrue("Cart 1: price ", cart.getTotalPrice().equals(new BigDecimal("29.83")));
        assertTrue("Cart 1: taxes ", cart.getTotalTaxes().equals(new BigDecimal("1.50")));

        assertTrue("Cart 2: taxes ", cart2.getTotalTaxes().equals(new BigDecimal("7.65")));
        assertTrue("Cart 2: price ", cart2.getTotalPrice().equals(new BigDecimal("65.15")));

        assertTrue("Cart 3: price ", cart3.getTotalPrice().equals(new BigDecimal("74.68")));
        assertTrue("Cart 3: taxes ", cart3.getTotalTaxes().equals(new BigDecimal("6.70")));
    }

    /**
     * tests that total taxes for medical type are zero with deep injection pattern
     *
     * @throws Exception
     */
    public void testDepInj() throws Exception {
        String input = "1 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85";

        Injector injector = Guice.createInjector(new DummyInjector());
        Parser parser = injector.getInstance(Parser.class);

        Cart cart = parser.parse(input);
        System.out.println(cart);

        assertTrue(cart.getTotalTaxes().equals(new BigDecimal("0.00")));

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
        ProductBuilder aProduct = ProductBuilder.newProductBuilder();
        Product product1 = aProduct
                .howMany(1)
                .ofType(BOOK)
                .withPrice("12.49")
                .isImported(false)
                .withDescription("book")
                .build();
        Product product2 = aProduct
                .howMany(1)
                .ofType(OTHER)
                .withPrice("14.99")
                .isImported(false)
                .withDescription("music CD")
                .build();
        Product product3 = aProduct
                .howMany(1)
                .ofType(FOOD)
                .withPrice("0.85")
                .isImported(false)
                .withDescription("chocolate bar")
                .build();

        Cart cart1 = new Cart()
                .addGood(product1)
                .addGood(product2)
                .addGood(product3);

        System.out.println(cart1);
        assertTrue("Cart 1: taxes ", cart1.getTotalTaxes().equals(new BigDecimal("1.50")));
        assertTrue("Cart 1: total ", cart1.getTotalPrice().equals(new BigDecimal("29.83")));
    }

    /**
     * Tests second order containing imported Products
     * 1 imported box of chocolates at 10.00
     * 1 imported bottle of perfume at 47.50
     *
     * @throws Exception
     */
    public void testOrder2() throws Exception {
        ProductBuilder aProduct = ProductBuilder.newProductBuilder();

        Product product1 = aProduct
                .ofType(FOOD)
                .withPrice("10.00")
                .withDescription("imported box of chocolates")
                .howMany(1)
                .isImported(true)
                .build();
        Product product2 = aProduct
                .howMany(1)
                .ofType(OTHER)
                .withPrice("47.50")
                .isImported(true)
                .withDescription("imported bottle of perfume")
                .build();


        Cart cart2 = new Cart()
                .addGood(product1)
                .addGood(product2);

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
        ProductBuilder aProduct = ProductBuilder.newProductBuilder();

        Product product1 = aProduct
                .howMany(1)
                .ofType(OTHER)
                .withPrice("27.99")
                .isImported(true)
                .withDescription("imported bottle of perfume")
                .build();
        Product product2 = aProduct
                .howMany(1)
                .ofType(OTHER)
                .withPrice("18.99")
                .isImported(false)
                .withDescription("bottle of perfume")
                .build();
        Product product3 = aProduct
                .howMany(1)
                .ofType(MEDICAL)
                .withPrice("9.75")
                .isImported(false)
                .withDescription("packet of headache pills")
                .build();
        Product product4 = aProduct
                .howMany(1)
                .ofType(FOOD)
                .withPrice("11.25")
                .isImported(true)
                .withDescription("box of imported chocolates")
                .build();

        Cart cart3 = new Cart()
                .addGood(product1)
                .addGood(product2)
                .addGood(product3)
                .addGood(product4);

        System.out.println(cart3);
        assertTrue("Cart 3: taxes ", cart3.getTotalTaxes().equals(new BigDecimal("6.70")));
        assertTrue("Cart 3: total ", cart3.getTotalPrice().equals(new BigDecimal("74.68")));

    }
}
