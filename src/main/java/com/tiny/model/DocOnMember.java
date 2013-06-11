package com.tiny.model;

import java.util.Date;

import lombok.Data;

@Data
public class DocOnMember {
	private Integer id;
	private String content;
	private String providerUserId;
	private Date regDate;
}