package com.itjenny.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

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
	private Boolean published;
	private String userId;
}