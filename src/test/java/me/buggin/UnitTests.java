package me.buggin;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigDecimal;

import static me.buggin.ProductType.*;

/**
 * Created by abuggin on 2/5/16.
 */
public class UnitTests extends TestCase{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public UnitTests(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static junit.framework.Test suite() {
        return new TestSuite(UnitTests.class);
    }

    /**
     * Tests FOOD product
     */
    public void testProductExempt() {
        Product product1 = ProductBuilder
                .newProductBuilder()
                .howMany(1)
                .ofType(FOOD)
                .withPrice("0.85")
                .isImported(false)
                .withDescription("chocolate bar")
                .build();
        assertTrue("chocolate pretax", product1.getPriceBeforeTaxes().equals(new BigDecimal("0.85")));
        assertTrue("chocolate postax", product1.getPriceAfterTaxes().equals(new BigDecimal("0.85")));
    }

    /**
     * Tests Exempted from tax Product
     *
     * @throws Exception
     */
    public void testAnotherProductExempt() throws Exception {
        Product product1 = ProductBuilder
                .newProductBuilder()
                .howMany(1)
                .ofType(BOOK)
                .withPrice("12.49")
                .isImported(false)
                .withDescription("book")
                .build();
        assertTrue("book pretax", product1.getPriceBeforeTaxes().equals(new BigDecimal("12.49")));
        assertTrue("book postax", product1.getPriceAfterTaxes().equals(new BigDecimal("12.49")));
    }

    /**
     * Tests OTHER type of food, (with tax)
     */
    public void testProduct() {
        Product product1 = ProductBuilder
                .newProductBuilder()
                .howMany(1)
                .ofType(OTHER)
                .withPrice("14.99")
                .isImported(false)
                .withDescription("music CD")
                .build();
        assertTrue("cd pretax", product1.getPriceBeforeTaxes().equals(new BigDecimal("14.99")));
        assertTrue("cd postax", product1.getPriceAfterTaxes().equals(new BigDecimal("16.49")));
    }
    /**
     * Testing of the round function
     *
     * @throws Exception
     */
    public void testRound() throws Exception {
        assertTrue(Product.round(new BigDecimal("0.03")).equals(new BigDecimal("0.05")));
        assertTrue(Product.round(new BigDecimal("0.033232")).equals(new BigDecimal("0.05")));
        assertTrue(Product.round(new BigDecimal("1.249")).equals(new BigDecimal("1.25")));
        assertTrue(Product.round(new BigDecimal("1.499")).equals(new BigDecimal("1.50")));
        assertTrue(Product.round(new BigDecimal("1.8999")).equals(new BigDecimal("1.90")));

    }
}
