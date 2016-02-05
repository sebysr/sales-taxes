package me.buggin;

/**
 * Always return <code>ProductType.MEDICAL</code>
 */
public class DummyProvider implements TypeProvider {
    @Override
    public ProductType getType(String key) {
        return ProductType.MEDICAL;
    }
}
