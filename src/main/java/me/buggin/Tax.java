package me.buggin;

import java.math.BigDecimal;

/**
 * Created by abuggin on 2/3/16.
 */
public class Tax extends BigDecimal{
    public static final Tax NONE = new Tax("0");
    public static final Tax IMPORT_TAX = new Tax("0.05");
    public static final Tax SALES_TAX = new Tax("0.1");

    public Tax(String i) {
        super(i);
    }
}
