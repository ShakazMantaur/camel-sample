package com.shakaz.camel.route;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DownloadAssetRoute extends SpringRouteBuilder {

    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:8081/assetstorage")
                .routeId("AssetStorageRoute")
                .to("file://tmp");
    }
}
