package me.buggin;

/**
 * Offer a parser that returns always MEDICAL
 */
public class DummyInjector implements TypeInjector {
    @Override
    public TypeRequester getParser() {
        return new Parser(new DummyProvider());
    }
}
