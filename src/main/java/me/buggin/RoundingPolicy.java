package me.buggin;

import java.math.BigDecimal;

/**
 * Created by abuggin on 2/5/16.
 */
public interface RoundingPolicy {
    BigDecimal round(BigDecimal amount);
}
