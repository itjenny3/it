package com.tiny.model;

import java.util.Date;

import lombok.Data;

@Data
public class Like {
	private String providerUserId;
	private Integer  documentId;
	// true : like
	// false : dislike
	private Boolean isLike;
	private Date regDate;
}