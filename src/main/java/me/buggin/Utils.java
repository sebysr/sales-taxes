package me.buggin;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Hello world!
 */
public class Utils {
    public static BigDecimal round(BigDecimal amount) {
        return new BigDecimal(
                Math.ceil(amount.doubleValue() * 20) / 20)
                .setScale(2, RoundingMode.HALF_UP);
    }

}
