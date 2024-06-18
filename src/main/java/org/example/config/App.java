package org.example.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:app.properties")
public interface App extends Config {
    @Key("url")
    String url();

    @Key("username")
    String username();

    @Key("password")
    String password();
}
