package me.buggin;

/**
 * Types of <code>Good</code>s:
 * first three are exempted,
 * <code>OTHER_TYPE</code> pays <code>SALES_TAX</code>
 */
public enum GoodType {
    FOOD_TYPE(Tax.NONE),
    BOOK_TYPE(Tax.NONE),
    MEDICAL_TYPE(Tax.NONE),
    OTHER_TYPE(Tax.SALES_TAX);

    private Tax salesTax;

    GoodType(Tax v) {
        this.salesTax = v;
    }

    public Tax getSalesTax() {
        return this.salesTax;
    }


}