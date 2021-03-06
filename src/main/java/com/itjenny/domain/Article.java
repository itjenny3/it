package com.itjenny.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

import org.hibernate.annotations.Index;

@Data
@Entity
public class Article {
    @Id
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Transient
    private String tag;
    @Index(name = "published")
    private Boolean published;
    @Index(name = "userId")
    private String userId;
    @Column(columnDefinition = "TEXT")
    private String css;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date updated;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}