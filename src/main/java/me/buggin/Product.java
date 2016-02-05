package me.buggin;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.math.BigDecimal;

/**
 * Main object representing the good purchased
 * <p>
 * Motivation for using <code>BigDecimal</code> :)
 * <p>
 * <a href="http://www-128.ibm.com/developerworks/java/library/j-jtp0114/">From an article by Brian Goetz:</a>
 * <p>
 * "...it is a bad idea to use floating point to try to represent exact quantities like monetary amounts.
 * Using floating point for dollars-and-cents calculations is a recipe for disaster.
 * Floating point numbers are best reserved for values such as measurements, whose values are fundamentally inexact to begin with."
 *
 * @author Alessandro Buggin
 */

public class Product {
    private int m_quantity;
    private String m_description;
    private ProductType m_type;
    private boolean m_isImported;
    private BigDecimal m_price;
    private BigDecimal m_taxes = new BigDecimal(0);

    private Injector injector = Guice.createInjector(new RoundingModule());
    private RoundingPolicy roundingPolicy = injector.getInstance(StandardRoundingPolicy.class);

    private Product(int quantity, String description, ProductType type, boolean isImported, BigDecimal price) {
        this.m_quantity = quantity;
        this.m_description = description;
        this.m_type = type;
        this.m_isImported = isImported;
        this.m_price = price;
    }

    /**
     * Factory method for Product object
     *
     * @param quantity
     * @param description
     * @param type
     * @param isExempt    if is a medical item, food or book, then is exempted from tax
     * @param price
     * @return
     */
    public static Product newProduct(int quantity, String description, ProductType type, boolean isExempt, BigDecimal price) {
        return new Product(quantity, description, type, isExempt, price);
    }



    public String toString() {
        String imported = (m_isImported) ? " imported " : " ";
        return m_quantity + imported + m_description + ": " + getPriceAfterTaxes();
    }

    public String toVerboseString() {
        return "#" + m_quantity
                + " of: "
                + m_description
                + " at "
                + getPriceAfterTaxes()
                + ": tax("
                + m_taxes
                + ") "
                + m_price
                + " ["
                + m_type
                + "]";
    }

    public BigDecimal getPriceBeforeTaxes() {
        return m_price;
    }

    public BigDecimal getTaxes() {
        if (m_taxes.equals(new BigDecimal(0))) {
            BigDecimal m_importTax = new BigDecimal(0);
            BigDecimal m_luxuryTax = m_price.multiply(m_type.getSalesTax());
            if (this.m_isImported) {
                m_importTax = m_price.multiply(Tax.IMPORT_TAX);
            }
            this.m_taxes = roundingPolicy.round(m_importTax.add(m_luxuryTax));
        }
        return this.m_taxes;
    }

    public BigDecimal getPriceAfterTaxes() {
        return (getTaxes().add(getPriceBeforeTaxes()));
    }
}
