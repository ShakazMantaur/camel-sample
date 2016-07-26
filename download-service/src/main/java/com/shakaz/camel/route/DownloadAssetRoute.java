package com.shakaz.camel.route;

import com.shakaz.camel.model.ContentUrl;
import com.shakaz.camel.model.TransferAsset;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.ObjectInputStream;

@Component
public class DownloadAssetRoute extends SpringRouteBuilder {

    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:8081/assetstorage")
                .routeId("AssetStorageRoute")
                .process(exchange -> {
                    InputStream is = exchange.getIn().getBody(InputStream.class);
                    ObjectInputStream ois = new ObjectInputStream(is);
                    TransferAsset ta = (TransferAsset)ois.readObject();
                    exchange.getIn().setBody(new ContentUrl(ta.getContentUrl()));
                })
                .to("file:tmpshakaz/?autoCreate=true&fileName=shaq.jpg");
    }
}
