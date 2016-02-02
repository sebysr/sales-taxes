package me.buggin;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Test FOOD
     */
    public void testGoodExempt() {
        Good good1 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.FOOD_TYPE)
                .setPrice(0.85)
                .setImported(false)
                .setDescription("chocolate bar")
                .build();
//        System.out.println(good1.getPriceAndTaxes());
        assertTrue("chocolate pretax",good1.getPricePreTaxes()==0.85);
        assertTrue("chocolate postax",good1.getPriceAndTaxes()==0.85);
    }

    /**
     * Test OTHER
     */
    public void testGood() {
        double price = 16.49;
        Good good1 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(GoodType.OTHER_TYPE)
                .setPrice(price)
                .setImported(false)
                .setDescription("music CD")
                .build();
//        System.out.println(good1.getPriceAndTaxes());
        assertTrue("cd pretax",good1.getPricePreTaxes()==price);
        assertTrue("cd postax",good1.getPriceAndTaxes()==18.14);
    }

    public void testOrder() throws Exception {
//        1 book : 12.49
//        1 music CD: 16.49
//        1 chocolate bar: 0.85

//        OrderBuilder orderBuilder = new OrderBuilder();
//        Order order1 = orderBuilder
//                .addGood(good1)
//                .addGood(good2)
//                .addGood(good3)
//                .build();
//
//        Assert(order1.totalTaxes().equals(1.50));
//        Assert(order1.totalPrice().equals(29.83));
        assertTrue(true);
    }
}
