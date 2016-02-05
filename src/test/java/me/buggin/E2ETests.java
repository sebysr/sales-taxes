package me.buggin;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigDecimal;

/**
 * Created by abuggin on 2/5/16.
 */
public class E2ETests
        extends TestCase {

    private Parser parser;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public E2ETests(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static junit.framework.Test suite() {
        return new TestSuite(E2ETests.class);
    }

    public void setUp() throws Exception {
        Injector injector = Guice.createInjector(new MapInjector());
        parser = injector.getInstance(Parser.class);
    }

    public void tearDown() throws Exception {
        parser = null;
    }

    public void testOrder1() throws Exception {
        String input = "1 book at 12.49\n" +
                "1 music CD at 14.99\n" +
                "1 chocolate bar at 0.85";
        Cart cart = parser.parse(input);
        System.out.println(cart);
        assertTrue("Cart 1: price ", cart.getTotalPrice().equals(new BigDecimal("29.83")));
        assertTrue("Cart 1: taxes ", cart.getTotalTaxes().equals(new BigDecimal("1.50")));
    }

    public void testOrder2() throws Exception {
        String input = "1 imported box of chocolates at 10.00\n" +
                "1 imported bottle of perfume at 47.50";
        Cart cart2 = parser.parse(input);
        System.out.println(cart2);
        assertTrue("Cart 2: taxes ", cart2.getTotalTaxes().equals(new BigDecimal("7.65")));
        assertTrue("Cart 2: price ", cart2.getTotalPrice().equals(new BigDecimal("65.15")));
    }

    public void testOrder3() throws Exception {
        String input = "1 imported bottle of perfume at 27.99\n" +
                "1 bottle of perfume at 18.99\n" +
                "1 packet of headache pills at 9.75\n" +
                "1 box of imported chocolates at 11.25";
        Cart cart3 = parser.parse(input);
        System.out.println(cart3);
        assertTrue("Cart 3: price ", cart3.getTotalPrice().equals(new BigDecimal("74.68")));
        assertTrue("Cart 3: taxes ", cart3.getTotalTaxes().equals(new BigDecimal("6.70")));

    }

}
