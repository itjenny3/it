package com.itjenny.service;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itjenny.common.CommonTest;

public class ArticleServiceTest extends CommonTest {
	@Autowired
	private ArticleService articleService;

	@Test
	public void testConvert() {
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
		String br = "first\nsecond";
		String p = "first\n\nsecond";

		// When
		h1 = articleService.convertToHtml("", h1);
		h1_2 = articleService.convertToHtml("", h1_2);
		h2 = articleService.convertToHtml("", h2);
		h2_2 = articleService.convertToHtml("", h2_2);
		h3 = articleService.convertToHtml("", h3);
		h4 = articleService.convertToHtml("", h4);
		h5 = articleService.convertToHtml("", h5);
		h6 = articleService.convertToHtml("", h6);
		indent = articleService.convertToHtml("", indent);
		indent2 = articleService.convertToHtml("", indent2);
		indent3 = articleService.convertToHtml("", indent3);
		italic = articleService.convertToHtml("", italic);
		italic2 = articleService.convertToHtml("", italic2);
		bold = articleService.convertToHtml("", bold);
		bold2 = articleService.convertToHtml("", bold2);
		link = articleService.convertToHtml("", link);
		imageLink = articleService.convertToHtml("", imageLink);
		quote = articleService.convertToHtml("", quote);
		line = articleService.convertToHtml("", line);
		number = articleService.convertToHtml("", number);
		br = articleService.convertToHtml("", br);
		p = articleService.convertToHtml("", p);

		// Then
		assertThat(h1, containsString("<h1>title</h1>\n"));
		assertThat(h1_2, containsString("<h1>title</h1>\n"));
		assertThat(h2, containsString("<h2>title</h2>\n"));
		assertThat(h2_2, containsString("<h2>title</h2>\n"));
		assertThat(h3, containsString("<h3>title</h3>\n"));
		assertThat(h4, containsString("<h4>title</h4>\n"));
		assertThat(h5, containsString("<h5>title</h5>\n"));
		assertThat(h6, containsString("<h6>title</h6>\n"));
		assertThat(indent, containsString("<ul>\n<li>indent</li>\n</ul>\n"));
		assertThat(indent2, containsString("<ul>\n<li>indent</li>\n</ul>\n"));
		assertThat(indent3, containsString("<ul>\n<li>indent</li>\n</ul>\n"));
		assertThat(italic, containsString("<p><em>italic</em></p>\n"));
		assertThat(italic2, containsString("<p><em>italic</em></p>\n"));
		assertThat(bold, containsString("<p><strong>bold</strong></p>\n"));
		assertThat(bold2, containsString("<p><strong>bold</strong></p>\n"));
		assertThat(link, containsString("<p><a href=\"http://link.url\">title</a></p>\n"));
		assertThat(imageLink,
				containsString("<p><a href=\"http://link.url\"><img src=\"http://image.url\" alt=\"alt\" /></a></p>\n"));
		assertThat(quote, containsString("<blockquote><p>quote</p>\n</blockquote>\n"));
		assertThat(line, containsString("<hr />\n"));
		assertThat(number, containsString("<ol>\n<li>with</li>\n<li>number</li>\n</ol>\n"));
		assertThat(br, containsString("<p>first\n<br  />second</p>\n"));
		assertThat(p, containsString("<p>first</p>\n<p>second</p>\n"));
	}
}