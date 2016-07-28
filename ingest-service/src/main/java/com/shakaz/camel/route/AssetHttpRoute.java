package com.shakaz.camel.route;

import com.shakaz.camel.dto.AssetDTO;
import com.shakaz.camel.exception.AssetNotFoundException;
import com.shakaz.camel.mapper.AssetDTOToAssetMapper;
import com.shakaz.camel.mapper.AssetToAssetDTOMapper;
import com.shakaz.camel.model.Asset;
import com.shakaz.camel.repository.AssetRepository;
import com.shakaz.camel.service.AssetService;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.shakaz.camel.route.ContentTransferRoute.directTransferContent;
import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

@Component
public class AssetHttpRoute extends SpringRouteBuilder {

    private AssetService assetService;

    @Autowired
    public AssetHttpRoute(AssetService assetService) {
        this.assetService = assetService;
    }

    public void configure() throws Exception {
        from("jetty:http://0.0.0.0:8080/asset?httpMethodRestrict=POST")
        .from("activemq:queue:asset?concurrentConsumers=5")
                .routeId("CreateAssetHttpRoute")
                .unmarshal().json(Gson, AssetDTO.class)
                .bean(new AssetDTOToAssetMapper(), "map")
                .to("jpa:com.shakaz.camel.model.Asset")
                .process(exchange -> System.out.println(exchange.getIn().getBody().toString()))
                .wireTap(directTransferContent)
                .bean(new AssetToAssetDTOMapper(), "map")
                .marshal().json(Gson).convertBodyTo(String.class);


        from("jetty:http://0.0.0.0:8080/asset?httpMethodRestrict=PUT")
                .routeId("ModifyAssetHttpRoute")
                .unmarshal().json(Gson, AssetDTO.class)
                .bean(new AssetDTOToAssetMapper(), "map")
                .bean(assetService, "fetchAsset")
                .choice()
                    .when(exchange -> exchange.getIn().getBody(Asset.class).getId() != null)
                        .to("jpa:com.shakaz.camel.model.Asset")
                        .process(exchange -> System.out.println(exchange.getIn().getBody().toString()))
                        .bean(new AssetToAssetDTOMapper(), "map")
                        .marshal().json(Gson).convertBodyTo(String.class)
                    .otherwise()
                        .throwException(new AssetNotFoundException())
                .end();
    }
}