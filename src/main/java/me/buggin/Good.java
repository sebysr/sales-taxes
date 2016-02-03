package me.buggin;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

public class Good {
    private int m_quantity;
    private String m_description;
    private GoodType m_type;
    private boolean m_isImported;
    private BigDecimal m_price;
    private BigDecimal m_taxes = new BigDecimal(0);


    private Good(int quantity, String description, GoodType type, boolean isImported, BigDecimal price) {
        this.m_quantity = quantity;
        this.m_description = description;
        this.m_type = type;
        this.m_isImported = isImported;
        this.m_price = price;
    }

    /**
     * Factory method for Good object
     *
     * @param quantity
     * @param description
     * @param type
     * @param isExempt    if is a medical item, food or book, then is exempted from tax
     * @param price
     * @return
     */
    public static Good newGood(int quantity, String description, GoodType type, boolean isExempt, BigDecimal price) {
        return new Good(quantity, description, type, isExempt, price);
    }

    /**
     * round amount following specification:
     * The rounding rules for sales tax are that for a tax rate of n%,
     * a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.
     *
     * @param amount
     * @return rounded amount
     */
    public static BigDecimal round(BigDecimal amount) {
        return new BigDecimal(
                Math.ceil(amount.doubleValue() * 20) / 20)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public String toString() {
        return m_quantity + " " + m_description + ": " + getPriceAfterTaxes();
    }

    public BigDecimal getPriceBeforeTaxes() {
        return m_price;
    }

    public BigDecimal getTaxes() {
        if (m_taxes.equals(new BigDecimal(0))) {
            BigDecimal m_importTax = new BigDecimal(0);
            BigDecimal m_luxuryTax = new BigDecimal(0);

            if (this.m_isImported) {
                m_importTax = m_price.multiply(new BigDecimal("0.05"));
            }
            if (this.m_type == (GoodType.OTHER_TYPE)) {
                m_luxuryTax = m_price.multiply(new BigDecimal("0.1"));
            }
            this.m_taxes = round(m_importTax.add(m_luxuryTax));
        }
        return this.m_taxes;
    }

    public BigDecimal getPriceAfterTaxes() {
        return (getTaxes().add(getPriceBeforeTaxes()));
    }
}
