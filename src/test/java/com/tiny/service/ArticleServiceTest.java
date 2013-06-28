package com.tiny.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiny.common.CommonTest;

public class ArticleServiceTest extends CommonTest {

	@Autowired
	private ArticleService articleService;

	@Test
	public void testParse() {
		// Given
		String h1 = "# title";
		String h1_2 = "title\n===";
		String h2 = "## title";
		String h2_2 = "title\n---";
		String h3 = "### title";
		String h4 = "#### title";
		String h5 = "##### title";
		String h6 = "###### title";
		String indent = " - indent";
		String indent2 = " * indent";
		String indent3 = " + indent";
		String italic = "_italic_";
		String italic2 = "*italic*";
		String bold = "__bold__";
		String bold2 = "**bold**";
		String link = "[title](http://link.url)";
		String imageLink = "[![alt](http://image.url)](http://link.url)";
		String quote = ">quote";
		String line = "----";
		String number = "1. with\n1. number";

		// When
		h1 = articleService.parse(h1);
		h1_2 = articleService.parse(h1_2);
		h2 = articleService.parse(h2);
		h2_2 = articleService.parse(h2_2);
		h3 = articleService.parse(h3);
		h4 = articleService.parse(h4);
		h5 = articleService.parse(h5);
		h6 = articleService.parse(h6);
		indent = articleService.parse(indent);
		indent2 = articleService.parse(indent2);
		indent3 = articleService.parse(indent3);
		italic = articleService.parse(italic);
		italic2 = articleService.parse(italic2);
		bold = articleService.parse(bold);
		bold2 = articleService.parse(bold2);
		link = articleService.parse(link);
		imageLink = articleService.parse(imageLink);
		quote = articleService.parse(quote);
		line = articleService.parse(line);
		number = articleService.parse(number);

		// Then
		assertThat(h1, is("<h1>title</h1>\n"));
		assertThat(h1_2, is("<h1>title</h1>\n"));
		assertThat(h2, is("<h2>title</h2>\n"));
		assertThat(h2_2, is("<h2>title</h2>\n"));
		assertThat(h3, is("<h3>title</h3>\n"));
		assertThat(h4, is("<h4>title</h4>\n"));
		assertThat(h5, is("<h5>title</h5>\n"));
		assertThat(h6, is("<h6>title</h6>\n"));
		assertThat(indent, is("<ul>\n<li>indent</li>\n</ul>\n"));
		assertThat(indent2, is("<ul>\n<li>indent</li>\n</ul>\n"));
		assertThat(indent3, is("<ul>\n<li>indent</li>\n</ul>\n"));
		assertThat(italic, is("<p><em>italic</em></p>\n"));
		assertThat(italic2, is("<p><em>italic</em></p>\n"));
		assertThat(bold, is("<p><strong>bold</strong></p>\n"));
		assertThat(bold2, is("<p><strong>bold</strong></p>\n"));
		assertThat(link, is("<p><a href=\"http://link.url\">title</a></p>\n"));
		assertThat(imageLink, is("<p><a href=\"http://link.url\"><img src=\"http://image.url\" alt=\"alt\" /></a></p>\n"));
		assertThat(quote, is("<blockquote><p>quote</p>\n</blockquote>\n"));
		assertThat(line, is("<hr />\n"));
		assertThat(number, is("<ol>\n<li>with</li>\n<li>number</li>\n</ol>\n"));
	}
}