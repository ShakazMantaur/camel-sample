package com.shakaz.camel.config;

import org.springframework.stereotype.Component;

@Component
public class JettyConfig {

    public String jettyAddress() {
        return "http://0.0.0.0:8080";
    }
}
