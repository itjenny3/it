package com.tiny.model;

import java.util.Date;

import lombok.Data;

@Data
public class MyDoc {
	private Integer myDocId;
	private String title;
	private String content;
	private String providerUserId;
	private String tag;
	private Boolean isGoal;
	private Date regDate;
	
	// undo xssFilter
	private String rawContent;
}