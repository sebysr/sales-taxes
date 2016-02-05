package me.buggin;

import com.google.inject.AbstractModule;

/**
 * Created by abuggin on 2/5/16.
 */
public class RoundingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RoundingPolicy.class).to(StandardRoundingPolicy.class);
    }
}
