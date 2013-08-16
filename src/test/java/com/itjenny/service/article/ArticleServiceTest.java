package com.itjenny.service.article;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

import com.itjenny.common.CommonTest;

public class ArticleServiceTest extends CommonTest {
	public void convert() {
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
//		h1 = htmlArticle.makeChapters("", h1);
//		h1_2 = htmlArticle.makeChapters("", h1_2);
//		h2 = htmlArticle.makeChapters("", h2);
//		h2_2 = htmlArticle.makeChapters("", h2_2);
//		h3 = htmlArticle.makeChapters("", h3);
//		h4 = htmlArticle.makeChapters("", h4);
//		h5 = htmlArticle.makeChapters("", h5);
//		h6 = htmlArticle.makeChapters("", h6);
//		indent = htmlArticle.makeChapters("", indent);
//		indent2 = htmlArticle.makeChapters("", indent2);
//		indent3 = htmlArticle.makeChapters("", indent3);
//		italic = htmlArticle.makeChapters("", italic);
//		italic2 = htmlArticle.makeChapters("", italic2);
//		bold = htmlArticle.makeChapters("", bold);
//		bold2 = htmlArticle.makeChapters("", bold2);
//		link = htmlArticle.makeChapters("", link);
//		imageLink = htmlArticle.makeChapters("", imageLink);
//		quote = htmlArticle.makeChapters("", quote);
//		line = htmlArticle.makeChapters("", line);
//		number = htmlArticle.makeChapters("", number);
//		br = htmlArticle.makeChapters("", br);
//		p = htmlArticle.makeChapters("", p);

		// Then
		assertThat(h1, containsString("<h1>title</h1>"));
		assertThat(h1_2, containsString("<h1>title</h1>"));
		assertThat(h2, containsString("<h1>title</h1>"));
		assertThat(h2_2, containsString("<h1>title</h1>"));
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