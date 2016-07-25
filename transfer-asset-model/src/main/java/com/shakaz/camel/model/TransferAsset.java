package com.shakaz.camel.model;

import java.io.Serializable;

public class TransferAsset implements Serializable{

    private String contentUrl;

    public TransferAsset(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }
}
