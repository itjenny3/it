package com.tiny.common.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiny.common.CommonTest;

public class XssFilterTest extends CommonTest {
	@Autowired
	private XssFilter xssFilter;

	@Test
	public void testEscapeHtml() {
		// Given
		String inputString = "<test>&";

		// When
		String ret = xssFilter.doFilter(inputString);

		// Then
		assertThat(ret, is("&lt;test&gt;&amp;"));
	}

	@Test
	public void testReplaceSpaceAndEnter() {
		// Given
		String inputString = "\r\n  ";

		// When
		String ret = xssFilter.doFilter(inputString);

		// Then
		assertThat(ret, is("<br/>&nbsp&nbsp"));
	}
}
