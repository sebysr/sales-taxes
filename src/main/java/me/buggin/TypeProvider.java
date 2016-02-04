package me.buggin;

/**
 * Service Provider for dependency injection <p>
 *     Abstract from implementation providing loose decoupling between implementation of how
 */
public interface TypeProvider {
    /**
     * from a string returns the best type
     * @param key
     * @return
     */
    GoodType getType(String key);
}
