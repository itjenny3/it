package com.itjenny.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;

import lombok.Data;

@Entity
@Data
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
}