package me.buggin;

import com.google.inject.AbstractModule;

/**
 * Concrete Injector
 */
public class MapInjector extends AbstractModule {
    public TypeRequester getParser() {
        return new Parser(new MapTypeProvider());
    }

    @Override
    protected void configure() {
        bind(TypeProvider.class).to(MapTypeProvider.class);
    }
}
