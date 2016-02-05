package me.buggin;
import com.google.inject.Singleton;

/**
 * Always return <code>ProductType.MEDICAL</code>
 */
@Singleton
public class DummyProvider implements TypeProvider {
    @Override
    public ProductType getType(String key) {
        return ProductType.MEDICAL;
    }
}
