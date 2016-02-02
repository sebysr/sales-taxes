package me.buggin;

import java.math.BigDecimal;

/**
 * Created by abuggin on 2/1/16.
 * 
 * From an article [http://www-128.ibm.com/developerworks/java/library/j-jtp0114/] by Brian Goetz:
 * "...it is a bad idea to use floating point to try to represent exact quantities like monetary amounts.
 * Using floating point for dollars-and-cents calculations is a recipe for disaster.
 * Floating point numbers are best reserved for values such as measurements, whose values are fundamentally inexact to begin with."
 */

public class Good {
    private int m_quantity;
    private String m_description;
    private GoodType m_type;
    private boolean m_isImported;
    private BigDecimal m_price;
    private BigDecimal m_importTax = new BigDecimal(0);
    private BigDecimal m_luxuryTax = new BigDecimal(0);

    private Good(int quantity, String description, GoodType type, boolean isImported, BigDecimal price) {
        this.m_quantity = quantity;
        this.m_description = description;
        this.m_type = type;
        this.m_isImported = isImported;
        this.m_price = price;

        computeTaxes();
    }

    public static Good newGood(int quantity, String description, GoodType type, boolean isExempt, BigDecimal price) {
        return new Good(quantity, description, type, isExempt, price);
    }

    private void computeTaxes() {
        if (this.m_isImported) {
            this.m_importTax = m_price.multiply(new BigDecimal("0.05"));
        }
        if (!isExempt()) {
            this.m_luxuryTax = m_price.multiply(new BigDecimal("0.1"));
        }
    }

    public String toString() {
        return m_quantity + " " + m_description + ": " + getPriceAndTaxes();
    }

    public BigDecimal getPricePreTaxes() {
        return m_price;
    }

    public BigDecimal getTaxes() {
        return Utils.round(m_importTax.add(m_luxuryTax));
    }

    public BigDecimal getPriceAndTaxes() {
        return (getTaxes().add(getPricePreTaxes()));
    }

    private boolean isExempt() {
        return m_type != (GoodType.OTHER_TYPE);
    }
}
