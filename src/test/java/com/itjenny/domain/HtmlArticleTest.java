package com.itjenny.domain;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import com.itjenny.common.CommonTest;
import com.itjenny.common.ResourceFile;
import com.itjenny.support.Const;

public class HtmlArticleTest extends CommonTest {
    @Rule
    public ResourceFile md1 = new ResourceFile("/sample/1.md");

    @Rule
    public ResourceFile md2 = new ResourceFile("/sample/2.md");

    private PegDownProcessor pegDownProcessor = new PegDownProcessor(
	    Extensions.ALL);
    
    @Test
    public void convertArticle() throws IOException {
	HtmlArticle htmlArticle = new HtmlArticle(pegDownProcessor, "", md2.getContent());
	System.out.println(htmlArticle.getChapters().toString());
    }

    @Test
    public void convertSection() {
	// Given
	String content = "# title0\nthis is first\nthis is second\n# title1\nthis is first\nthis is second.";

	// When
	HtmlArticle htmlArticle = new HtmlArticle(pegDownProcessor, "", content);

	// Then
	assertThat(htmlArticle.getChapters().size(), is(1));
	List<Section> sections = htmlArticle.getChapters().get(0).getSections();
	for (int i = 0; i < htmlArticle.getChapters().size(); i++) {
	    assertThat(sections.get(i).getSubtitle(), is("title" + i));
	    assertThat(sections.get(i).getContent(),
		    containsString("this is first"));
	}
    }

    @Test
    public void convertQuiz() {
	// Given
	String content = "# QUIZ\nquestion1\n>answer1\n";

	// When
	HtmlArticle htmlArticle = new HtmlArticle(pegDownProcessor, "", content);

	// Then
	assertThat(htmlArticle.getChapters().size(), is(1));
	List<Chapter> chapters = htmlArticle.getChapters();
	for (int i = 0; i < htmlArticle.getChapters().size(); i++) {
	    assertThat(chapters.get(i).getQuiz().getSubtitle(), is(Const.QUIZ));
	    assertThat(chapters.get(i).getQuiz().getContent(),
		    containsString("question1"));
	    assertThat(chapters.get(i).getQuiz().getAnswer(), is("answer1"));
	}
    }

    @Test
    public void convertChapter() {
	// Given
	String content = "# title0\nthis is first\nthis is second\n# title1\nthis is first\nthis is second.\n# QUIZ\nquestion1\n>answer1\n";

	// When
	HtmlArticle htmlArticle = new HtmlArticle(pegDownProcessor, "", content);

	// Then
	assertThat(htmlArticle.getChapters().size(), is(1));
    }

    @Test
    public void testChapter2() {
	// Given
	String content = "# title0\nthis is first\nthis is second\n# title1\nthis is first\nthis is second.\n# QUIZ\nquestion1\n>answer1\n";
	String content2 = content + content;

	// When
	HtmlArticle htmlArticle = new HtmlArticle(pegDownProcessor, "",
		content2);

	// Then
	assertThat(htmlArticle.getChapters().size(), is(2));
    }
}