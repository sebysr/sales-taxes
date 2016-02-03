package me.buggin;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.List;

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

    public Tax getSalesTax(){
        return this.salesTax;
    }

    GoodType(Tax v) {
        this.salesTax = v;
    }

    private static ImmutableMap<String, GoodType> dictionary = ImmutableMap.<String, GoodType>builder()
            .put("book", GoodType.BOOK_TYPE)
            .put("chocolate", GoodType.FOOD_TYPE)
            .put("pills", GoodType.MEDICAL_TYPE)
            .put("wine", GoodType.FOOD_TYPE)
            .put("bread", GoodType.FOOD_TYPE)
            .build();

    public static GoodType parseType(List<String> desc) {
        for (String x: desc){
            GoodType potential = GoodType.getTypeFromMap(x);
            if( potential != null){
                return potential;
            }
        }
        return GoodType.OTHER_TYPE;
    }

    private static String dropSChar(String key){
        if(key.charAt(key.length()-1)=='s'){
            key = key.substring(0,key.length()-2);
        }
        return key;
    }

    private static GoodType getTypeFromMap(String key){
        key = dropSChar(key);
        if(dictionary.containsKey(key))
            return dictionary.get(key);
        return null;
    }


}