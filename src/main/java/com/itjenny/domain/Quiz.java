package com.itjenny.domain;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

@Data
public class Quiz {
	private Integer index;
	private String css = StringUtils.EMPTY;
	private String subtitle = StringUtils.EMPTY;
	private String content = StringUtils.EMPTY;
	private String answer = StringUtils.EMPTY;
}