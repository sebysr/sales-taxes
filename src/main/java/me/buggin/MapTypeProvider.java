package me.buggin;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Singleton;

/**
 * Concrete Provider for ProductType implemented with hardcoded Map
 * <p>
 * This can be conceived as a dummy class, since it checks only a bunch of keys.
 * But since it implements a dependency injection pattern, another class can extend its functionality, maybe using a DB or other IO
 */
@Singleton
public class MapTypeProvider implements TypeProvider {

    private static ImmutableMap<String, ProductType> dictionary = ImmutableMap.<String, ProductType>builder()
            .put("book", ProductType.BOOK)
            .put("chocolate", ProductType.FOOD)
            .put("pills", ProductType.MEDICAL)
            .put("wine", ProductType.FOOD)
            .put("bread", ProductType.FOOD)
            .build();

    /**
     * drops 's' and 'es' char to match even plural nouns
     * don't exclude already matching 's' nouns
     * complex NLP problem: there are going to be a lot of exceptions
     *
     * @param key
     * @return
     */
    private static ProductType getTypeFromMap(String key) {
        ProductType potential = justGetTypeFromMap(key);
        if (potential != null) {
            return potential;
        } else {
            key = fromPlural(key);
            return justGetTypeFromMap(key);
        }
    }

    private static ProductType justGetTypeFromMap(String key) {
        if (MapTypeProvider.dictionary.containsKey(key)) {
            return MapTypeProvider.dictionary.get(key);
        }
        return null;
    }

    /**
     * Returns the singular form from a plural noun
     *
     * @param str plural
     * @return singular
     */
    private static String fromPlural(String str) {
        if (str.toLowerCase().endsWith("es") && !shouldEndWithE(str)) {
            return str.substring(0, str.toLowerCase().lastIndexOf("es"));
        } else {
            if (str.toLowerCase().endsWith("s")) {
                return str.substring(0, str.toLowerCase().lastIndexOf('s'));
            } else return str;
        }
    }

    /**
     * @param str
     * @return true if the singular form of a word should end with the letter "e"
     */
    private static boolean shouldEndWithE(String str) {
        return str.toLowerCase().endsWith("iece")
                || str.toLowerCase().endsWith("ice")
                || str.toLowerCase().endsWith("ace")
                || str.toLowerCase().endsWith("ise")
                || str.toLowerCase().endsWith("ates")
                ;
    }

    /**
     * Implementation of Interface for Dep.Inj.
     *
     * @param key
     * @return the type corresponding to the key if present in the hard-coded map
     */
    @Override
    public ProductType getType(String key) {
        return getTypeFromMap(key);
    }
}
