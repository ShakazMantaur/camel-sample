package com.shakaz.camel.routes;

import com.shakaz.camel.config.CouchDbConfig;
import com.shakaz.camel.config.JettyConfig;
import com.shakaz.camel.model.Asset;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class AssetHttpRoute extends SpringRouteBuilder {

    private final JettyConfig jettyConfig;
    private final CouchDbConfig couchDbConfig;

    @Autowired
    public AssetHttpRoute(JettyConfig jettyConfig, CouchDbConfig couchDbConfig) {
        this.jettyConfig = jettyConfig;
        this.couchDbConfig = couchDbConfig;
    }

    public void configure() throws Exception {
        from("jetty:" + jettyConfig.jettyAddress() + "/asset?httpMethodRestrict=POST")
                .routeId("AssetHttpRoute")
                .unmarshal().json(Gson, Asset.class)
                .marshal().json(Gson).convertBodyTo(String.class)
                .to("couchdb:" + couchDbConfig.assetsDatabase() + "?createDatabase=true");
    }
}
