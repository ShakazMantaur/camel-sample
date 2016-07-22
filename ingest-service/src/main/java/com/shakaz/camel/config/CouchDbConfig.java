package com.shakaz.camel.config;

import org.springframework.stereotype.Component;

@Component
public class CouchDbConfig {

    public String assetsDatabase() {
        return "http://localhost:5984/assets";
    }
}
