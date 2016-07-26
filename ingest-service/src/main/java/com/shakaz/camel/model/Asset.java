package com.shakaz.camel.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@EntityListeners({AuditingEntityListener.class})
@Entity
@Data
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    private String contentUrl;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    Date createTime;

    public boolean isIngestedWithContentUrl() {
        return getContentUrl() != null;
    }
}
