package me.buggin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent a set of <code>Product</code>s purchased together;
 * uses builder Pattern
 */
public class Cart {
    private List<Product> m_listOfProducts;
    private BigDecimal m_totTaxes = new BigDecimal(0);
    private BigDecimal m_totPrices = new BigDecimal(0);

    public Cart() {
        m_listOfProducts = new ArrayList<>();
    }

    public Cart addGood(Product product) {
        this.m_listOfProducts.add(product);
        m_totTaxes = m_totTaxes.add(product.getTaxes());
        m_totPrices = m_totPrices.add(product.getPriceAfterTaxes());
        return this;
    }

    public BigDecimal getTotalTaxes() {
        return (m_totTaxes);
    }

    public BigDecimal getTotalPrice() {
        return (m_totPrices);
    }

    /**
     * @return receipt for whole basket
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Product g : m_listOfProducts) {
            sb.append(g);
//            sb.append(g.toVerboseString());
            sb.append("\n");
        }

        sb.append("Sales Tax: ");
        sb.append(getTotalTaxes());
        sb.append("\n");
        sb.append("Total: ");
        sb.append(getTotalPrice());
        sb.append("\n");

        return sb.toString();
    }
}
