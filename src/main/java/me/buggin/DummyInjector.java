package me.buggin;

import com.google.inject.AbstractModule;

/**
 * Offer a parser that returns always MEDICAL
 */
public class DummyInjector extends AbstractModule {
//    @Override
//    public TypeRequester getParser() {
//        return new Parser(new DummyProvider());
//    }

    @Override
    protected void configure() {
        bind(TypeProvider.class).to(DummyProvider.class);
    }
}
