package com.shakaz.camel.service;

import com.shakaz.camel.model.Asset;
import com.shakaz.camel.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AssetService {

    private AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Transactional(readOnly = true)
    public void fetchAsset(Asset asset) {
        String name = asset.getName();
        assetRepository.findByName(name).ifPresent(orgAsset -> fetchAssetData(asset, orgAsset));
    }

    private void fetchAssetData(Asset asset, Asset orgAsset) {
        asset.setCreateTime(orgAsset.getCreateTime());
        asset.setId(orgAsset.getId());
    }
}
