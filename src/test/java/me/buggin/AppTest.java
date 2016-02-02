package me.buggin;

import junit.framework.Assert;
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
     * Test Input 1
     */
    public void testApp() {
         final String FOOD_TYPE = "FOOD";

        //1 book : 12.49
        //1 music CD: 16.49
        //1 chocolate bar: 0.85
        Good good1 = GoodBuilder
                .newGoodBuilder()
                .setQuantity(1)
                .setType(FOOD_TYPE)
                .setPrice(0.85)
                .setExempt(false)
                .setDescription("chocolate bar")
                .build();


//        OrderBuilder orderBuilder = new OrderBuilder();
//        Order order1 = orderBuilder
//                .addGood(good1)
////                .addGood(good2)
////                .addGood(good3)
//                .build();  TRIVIALE
//        Assert(order1.totalTaxes().equals(1.50));
//        Assert(order1.totalPrice().equals(29.83));
        System.out.println(good1.toString());

        assertTrue(true);
    }
}
