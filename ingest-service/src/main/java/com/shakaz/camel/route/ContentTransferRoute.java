package com.shakaz.camel.route;

import com.shakaz.camel.mapper.AssetToTransferAssetMapper;
import com.shakaz.camel.model.Asset;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import static com.shakaz.camel.processor.Processors.testProcessor;

@Component
public class ContentTransferRoute extends SpringRouteBuilder {

    public static final String directTransferContent = "direct:transferContent";

    @Override
    public void configure() throws Exception {
        from(directTransferContent)
                .routeId("TransferAssetContent")
                .choice()
                    .when(exchange -> exchange.getIn().getBody(Asset.class).isIngestedWithContentUrl())
                        .bean(new AssetToTransferAssetMapper(), "map")
                        .process(testProcessor)
                        .to("http://0.0.0.0:8081/assetstorage?bridgeEndpoint=true&throwExceptionOnFailure=false")
                .end();
    }
}