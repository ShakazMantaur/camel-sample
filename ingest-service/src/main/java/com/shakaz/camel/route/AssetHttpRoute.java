package com.shakaz.camel.route;

import com.shakaz.camel.dto.AssetDTO;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class AssetHttpRoute extends SpringRouteBuilder {

    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:8080/asset?httpMethodRestrict=POST")
        //.from("activemq:queue:asset")
                .routeId("AssetHttpRoute")
                .unmarshal().json(Gson, AssetDTO.class)
                .marshal().json(Gson).convertBodyTo(String.class)
                .to("couchdb:http://localhost:5984/assets?createDatabase=true");
    }
}
