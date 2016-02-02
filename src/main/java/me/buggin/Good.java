package me.buggin;

/**
 * Created by abuggin on 2/1/16.
 */
public class Good {
    private int m_quantity;
    private String m_description;
    private String m_type;
    private boolean m_isExempt;
    private double m_price;

    private Good(int quantity, String description, String type, boolean isExempt, double price){
        this.m_quantity = quantity;
        this.m_description = description;
        this.m_type = type;
        this.m_isExempt = isExempt;
        this.m_price = price;
    }
    public static Good newGood(int quantity, String description, String type, boolean isExempt, double price){
        return new Good(quantity, description, type, isExempt, price);
    }
    public String toString(){
        return "#"+ m_quantity +" of "+ m_description +" ("+ m_type + ") : "+ m_price;
    }
}
