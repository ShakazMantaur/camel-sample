package com.shakaz.camel.route;

import com.shakaz.camel.dto.AssetDTO;
import com.shakaz.camel.mapper.AssetDTOToAssetMapper;
import com.shakaz.camel.mapper.AssetToAssetDTOMapper;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import static com.shakaz.camel.route.ContentTransferRoute.directTransferContent;
import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class AssetHttpRoute extends SpringRouteBuilder {

    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:8080/asset?httpMethodRestrict=POST")
        //.from("activemq:queue:asset")
                .routeId("AssetHttpRoute")
                .unmarshal().json(Gson, AssetDTO.class)
                .bean(new AssetDTOToAssetMapper(), "map")
                .to("jpa:com.shakaz.camel.model.Asset")
                .process(exchange -> System.out.println(exchange.getIn().getBody().toString()))
               /* .choice()
                    .when(exchange -> exchange.getIn().getBody(Asset.class).isIngestedWithContentUrl())
                        .bean(new AssetToTransferAssetMapper(), "map")
                        .process(testProcessor)
                        .to("http://0.0.0.0:8081/assetstorage?bridgeEndpoint=true&throwExceptionOnFailure=false")
                .end()*/
                .wireTap(directTransferContent)
                .bean(new AssetToAssetDTOMapper(), "map")
                .marshal().json(Gson).convertBodyTo(String.class);
    }
}