package com.itjenny.model;

import lombok.Data;

import org.apache.commons.lang.StringUtils;

@Data
public class Quiz {
	private String id = StringUtils.EMPTY;
	private String nextid = StringUtils.EMPTY;
	private String css = StringUtils.EMPTY;
	private String subtitle = StringUtils.EMPTY;
	private String content = StringUtils.EMPTY;
	private String answer = StringUtils.EMPTY;
}