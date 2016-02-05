package me.buggin;

import java.math.BigDecimal;

/**
 * Builder patter for <code>Product</code> object
 */
public class ProductBuilder {
    private int m_quantity = 1;
    private String m_description;
    private ProductType m_type;
    private boolean m_isImported = false;
    private BigDecimal m_price;

    public static ProductBuilder newProductBuilder() {
        return new ProductBuilder();
    }

    public ProductBuilder howMany(int quantity) {
        this.m_quantity = quantity;
        return this;
    }

    public ProductBuilder withPrice(BigDecimal price) {
        this.m_price = price;
        return this;
    }

    public ProductBuilder withDescription(String desc) {
        this.m_description = desc;
        return this;
    }

    public ProductBuilder ofType(ProductType type) {
        this.m_type = type;
        return this;
    }

    public ProductBuilder isImported(boolean exempt) {
        this.m_isImported = exempt;
        return this;
    }

    public Product build() {
        return Product.newProduct(m_quantity, m_description, m_type, m_isImported, m_price);
    }

    public ProductBuilder withPrice(String s) {
        return this.withPrice(new BigDecimal(s));
    }
}
