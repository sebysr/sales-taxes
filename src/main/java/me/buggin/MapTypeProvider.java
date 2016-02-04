package me.buggin;

import com.google.common.collect.ImmutableMap;

/**
 * Concrete Provider for GoodType implemented with hardcoded Map
 * <p>
 *     This can be conceived as a dummy class, since it checks only a bunch of keys.
 *     But since it implements a dependency injection pattern, another class can extend its functionality, maybe using a DB or other IO
 */
public class MapTypeProvider implements TypeProvider {

    private static ImmutableMap<String, GoodType> dictionary = ImmutableMap.<String, GoodType>builder()
            .put("book", GoodType.BOOK_TYPE)
            .put("chocolate", GoodType.FOOD_TYPE)
            .put("pills", GoodType.MEDICAL_TYPE)
            .put("wine", GoodType.FOOD_TYPE)
            .put("bread", GoodType.FOOD_TYPE)
            .build();

    public static GoodType getTypeFromMap(String key) {
        key = dropSChar(key);
        if (MapTypeProvider.dictionary.containsKey(key))
            return MapTypeProvider.dictionary.get(key);
        return null;
    }

    private static String dropSChar(String key) {
        if (key.charAt(key.length() - 1) == 's') {
            key = key.substring(0, key.length() - 2);
        }
        return key;
    }

    @Override
    public GoodType getType(String key) {
        return getTypeFromMap(key);
    }
}
