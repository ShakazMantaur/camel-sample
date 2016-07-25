package com.shakaz.camel.model;

public class Asset {

    private String _id;
    private String _rev;
    private String name;
    private String contentUrl;

    private AssetMetadata assetMetadata;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getRevision() {
        return _rev;
    }

    public void setRevision(String revision) {
        this._rev = revision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public AssetMetadata getAssetMetadata() {
        return assetMetadata;
    }

    public void setAssetMetadata(AssetMetadata assetMetadata) {
        this.assetMetadata = assetMetadata;
    }

    public boolean ingestedWithContent() {
        return getContentUrl() != null;
    }
}
