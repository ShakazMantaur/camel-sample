package com.shakaz.camel.mapper;

import com.shakaz.camel.model.Asset;
import com.shakaz.camel.model.TransferAsset;

public class AssetToTransferAssetMapper {

    public TransferAsset map(Asset asset) {
        return new TransferAsset(asset.getContentUrl());
    }
}
