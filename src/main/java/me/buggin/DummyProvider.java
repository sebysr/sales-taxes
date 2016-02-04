package me.buggin;

/**
 * Always return <code>GoodType.MEDICAL_TYPE</code>
 */
public class DummyProvider implements TypeProvider {
    @Override
    public GoodType getType(String key) {
        return GoodType.MEDICAL_TYPE;
    }
}
