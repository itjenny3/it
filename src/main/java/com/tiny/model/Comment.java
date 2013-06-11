package com.tiny.model;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
	private Integer commentId;
	private Integer  documentId;
	private String content;
	private String providerUserId;
	private String name;
	private Boolean isMyDoc;
	private Date regDate;
}
