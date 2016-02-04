package me.buggin;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represent a set of <code>Good</code>s purchased together;
 * uses builder Pattern
 */
public class Cart {
    private List<Good> m_listOfGoods;
    private BigDecimal m_totTaxes = new BigDecimal(0);
    private BigDecimal m_totPrices = new BigDecimal(0);

    private Cart(List<Good> listOfGoods) {
        m_listOfGoods = listOfGoods;
        for (Good g : listOfGoods) {
            m_totTaxes = m_totTaxes.add(g.getTaxes());
            m_totPrices = m_totPrices.add(g.getPriceAfterTaxes());
        }
    }

    /**
     * Factory method
     *
     * @param listOfGoods
     * @return
     */
    public static Cart newCart(List<Good> listOfGoods) {
        return new Cart(listOfGoods);
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

        for (Good g : m_listOfGoods) {
            sb.append(g + "\n");
        }

        sb.append("Sales Tax: " + getTotalTaxes() + "\n");
        sb.append("Total: " + getTotalPrice() + "\n");

        return sb.toString();
    }
}