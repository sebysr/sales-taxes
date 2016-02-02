package me.buggin;

/**
 * Created by abuggin on 2/1/16.
 */
public class Good {
    private int m_quantity;
    private String m_description;
    private GoodType m_type;
    private boolean m_isImported;
    private double m_price;

    private Good(int quantity, String description, GoodType type, boolean isImported, double price){
        this.m_quantity = quantity;
        this.m_description = description;
        this.m_type = type;
        this.m_isImported = isImported;
        this.m_price = price;
    }

    public static Good newGood(int quantity, String description, GoodType type, boolean isExempt, double price){
        return new Good(quantity, description, type, isExempt, price);
    }

    public String toString(){
        String imported = (m_isImported) ? " imported " : " ";
        return m_quantity + imported + m_description +" ("+ m_type + ") : "+ m_price;
    }

    public double getPricePreTaxes(){
        return m_price;
    }

    public double getPriceAndTaxes(){
        double taxedPrice = m_price;
        if(m_isImported){
            double tax = m_price*0.05;
            taxedPrice+=tax;
        }
        if(!isExempt()){
            double tax = m_price*0.1;
            taxedPrice+= tax;
        }
        return App.format(taxedPrice);
    }

    private boolean isExempt(){
//        System.out.println(m_type!=GoodType.OTHER_TYPE);
        return m_type!=(GoodType.OTHER_TYPE);
    }
}
