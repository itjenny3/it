package com.tiny.common.util;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Component;

@Component
public class XssFilter {
	public String doFilter(String input) {
		if (input == null) {
			return "";
		}
		String escapedHtml = StringEscapeUtils.escapeHtml(input);
		return escapedHtml.replace("\r\n", "<br/>").replace("\n", "<br/>").replace(" ", "&nbsp");
	}
	
	public String undoFilter(String input) {
		if (input == null) {
			return "";
		}
		return input.replace("<br/>", "\n").replace("&nbsp", " ");
	}
}