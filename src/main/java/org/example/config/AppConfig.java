package org.example.config;

import org.aeonbits.owner.ConfigFactory;

public class AppConfig {
    private static App config;

    public static final String BASE_URL = get().url();

    public static App get() {
        if (config == null) {
            config = ConfigFactory.create(App.class, System.getProperties());
        }
        return config;
    }
}
