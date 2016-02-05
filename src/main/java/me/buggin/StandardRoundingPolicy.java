package me.buggin;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by abuggin on 2/5/16.
 */
public class StandardRoundingPolicy implements RoundingPolicy {
    /**
     * round amount following specification:
     * The rounding rules for sales tax are that for a tax rate of n%,
     * a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.
     *
     * @param amount
     * @return rounded amount
     */
    public BigDecimal round(BigDecimal amount) {
        return new BigDecimal(
                Math.ceil(amount.doubleValue() * 20) / 20)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
