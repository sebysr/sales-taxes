package me.buggin;

/**
 * Types of <code>Product</code>s:
 * first three are exempted,
 * <code>OTHER</code> pays <code>SALES_TAX</code>
 */
public enum ProductType {
    FOOD(Tax.NONE),
    BOOK(Tax.NONE),
    MEDICAL(Tax.NONE),
    OTHER(Tax.SALES_TAX);

    private Tax salesTax;

    ProductType(Tax v) {
        this.salesTax = v;
    }

    public Tax getSalesTax() {
        return this.salesTax;
    }


}