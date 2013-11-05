package com.itjenny.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Theme {
    @Id
    private String title;
    @Column(columnDefinition = "TEXT")
    private String css;
    private String userId;
}