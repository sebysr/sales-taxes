package me.buggin;

/**
 * Concrete Injector
 */
public class MapInjector implements TypeInjector {
    public TypeRequester getParser() {
        return new Parser(new MapTypeProvider());
    }
}
