package me.buggin;

/**
 * Created by abuggin on 2/1/16.
 */
public class GoodBuilder {
    private int m_quantity = 1;
    private String m_description;
    private String m_type;
    private boolean m_isExempt = false;
    private double m_price;

    public static GoodBuilder newGoodBuilder() {
        return new GoodBuilder();
    }

    public GoodBuilder setQuantity(int quantity){
        this.m_quantity = quantity;
        return this;
    }
    public GoodBuilder setPrice(double price){
        this.m_price = price;
        return this;
    }
    public GoodBuilder setDescription(String desc){
        this.m_description = desc;
        return this;
    }
    public GoodBuilder setType(String type){
        this.m_type = type;
        return this;
    }
    public GoodBuilder setExempt(boolean exempt){
        this.m_isExempt = exempt;
        return this;
    }
    public Good build(){
        return Good.newGood(m_quantity, m_description, m_type, m_isExempt, m_price);
    }
}