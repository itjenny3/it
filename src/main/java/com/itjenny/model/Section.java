package com.itjenny.model;

import org.apache.commons.lang.StringUtils;

import lombok.Data;

@Data
public class Section {
	private String id = StringUtils.EMPTY;
	private String nextid = StringUtils.EMPTY;
	private String css = StringUtils.EMPTY;
	private String subtitle = StringUtils.EMPTY;
	private String content = StringUtils.EMPTY;
}