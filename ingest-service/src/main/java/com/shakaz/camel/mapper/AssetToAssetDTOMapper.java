package com.shakaz.camel.mapper;

import com.shakaz.camel.dto.AssetDTO;
import com.shakaz.camel.model.Asset;

public class AssetToAssetDTOMapper {

    public AssetDTO map(Asset asset) {
        AssetDTO assetDTO = new AssetDTO();

        assetDTO.setContentUrl(asset.getContentUrl());
        assetDTO.setName(asset.getName());

        return assetDTO;
    }
}
