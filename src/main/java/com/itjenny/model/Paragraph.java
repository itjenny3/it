package com.itjenny.model;

import org.apache.commons.lang.StringUtils;

import lombok.Data;

@Data
public class Paragraph {
	private String id = StringUtils.EMPTY;
	private String css = StringUtils.EMPTY;
	private String subtitle = StringUtils.EMPTY;
	private String content = StringUtils.EMPTY;
	private String answer = StringUtils.EMPTY;
	private String hint = StringUtils.EMPTY;
}