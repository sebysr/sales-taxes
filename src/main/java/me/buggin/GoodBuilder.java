package me.buggin;

import java.math.BigDecimal;

/**
 * Builder patter for <code>Good</code> object
 *
 */
public class GoodBuilder {
    private int m_quantity = 1;
    private String m_description;
    private GoodType m_type;
    private boolean m_isImported = false;
    private BigDecimal m_price;

    public static GoodBuilder newGoodBuilder() {
        return new GoodBuilder();
    }

    public GoodBuilder setQuantity(int quantity) {
        this.m_quantity = quantity;
        return this;
    }

    public GoodBuilder setPrice(BigDecimal price) {
        this.m_price = price;
        return this;
    }

    public GoodBuilder setDescription(String desc) {
        this.m_description = desc;
        return this;
    }

    public GoodBuilder setType(GoodType type) {
        this.m_type = type;
        return this;
    }

    public GoodBuilder setImported(boolean exempt) {
        this.m_isImported = exempt;
        return this;
    }

    public Good build() {
        return Good.newGood(m_quantity, m_description, m_type, m_isImported, m_price);
    }

    public GoodBuilder setPrice(String s) {
        return this.setPrice(new BigDecimal(s));
    }
}
