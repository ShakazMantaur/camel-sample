package com.shakaz.camel.mapper;

import com.shakaz.camel.dto.AssetDTO;
import com.shakaz.camel.model.Asset;

public class AssetDTOToAssetMapper {

    public Asset map(AssetDTO assetDTO) {
        Asset asset = new Asset();

        asset.setContentUrl(assetDTO.getContentUrl());
        asset.setName(assetDTO.getName());

        return asset;
    }
}
