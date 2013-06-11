package com.tiny.model;

import java.util.Date;

import lombok.Data;

@Data
public class Document {
	private Integer documentId;
	private String content;
	private Integer point;
	private Integer like;
	private Integer dislike;
	private Integer comment;
	private Integer sharing;
	private String providerUserId;
	private String name;
	private Date regDate;
	
	// from Like
	private Boolean hasMyLike;
	private Boolean hasMyDislike;
	
	// undo xssFilter
	private String rawContent;
}