package com.itjenny.domain;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

@Data
public class Section {
	private String css = StringUtils.EMPTY;
	private String subtitle = StringUtils.EMPTY;
	private String content = StringUtils.EMPTY;
}