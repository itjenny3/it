package com.itjenny.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Article {
	@Id
	private String title;
	@Column(columnDefinition = "TEXT")
	private String content;
	private Integer published;
}