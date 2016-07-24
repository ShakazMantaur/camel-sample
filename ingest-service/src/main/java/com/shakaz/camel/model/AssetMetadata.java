package com.shakaz.camel.model;

public class AssetMetadata {

    private String name;
    private String description;
    private String createDate;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
