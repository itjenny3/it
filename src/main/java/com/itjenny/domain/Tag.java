package com.itjenny.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

import org.hibernate.annotations.Index;

@Data
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Index(name = "tag")
    private String tag;
    @Index(name = "article")
    private String article;
}