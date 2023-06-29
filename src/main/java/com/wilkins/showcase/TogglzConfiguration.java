package com.wilkins.showcase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;
import org.togglz.core.util.NamedFeature;

@Configuration
public class TogglzConfiguration implements TogglzConfig {

    private final StateRepository stateRepository;

    public TogglzConfiguration(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public Class<? extends Feature> getFeatureClass() {
        return NamedFeature.class;
    }

    @Override
    public StateRepository getStateRepository() {
        return stateRepository;
    }

    @Override
    @Bean
    public UserProvider getUserProvider() {
        return () -> new SimpleFeatureUser("admin", true);
    }
}
