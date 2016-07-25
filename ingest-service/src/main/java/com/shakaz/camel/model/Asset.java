package com.shakaz.camel.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(unique = true)
    private String name;

    private String contentUrl;

    public boolean isIngestedWithContentUrl() {
        return getContentUrl() != null;
    }
}
