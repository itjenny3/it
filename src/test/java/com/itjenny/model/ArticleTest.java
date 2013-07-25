package com.itjenny.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.itjenny.common.CommonTest;
import com.itjenny.common.util.Const;

public class ArticleTest extends CommonTest {
	private Article article = new Article();

	@Test
	public void checkQuiz() {
		// Given
		String quiz = "# QUIZ\nquestion1\nanswer>answer1\nhint>hint1";
		article.setContent(quiz);

		// When
		article.makeParagraphs();

		// Then
		assertThat(article.getParagraphs().size(), is(1));
		List<Paragraph> sentences = article.getParagraphs();
		for (int i = 0; i < article.getParagraphs().size(); i++) {
			assertThat(sentences.get(i).getSubtitle(), is(Const.QUIZ));
			assertThat(sentences.get(i).getContent(), containsString("question1"));
			assertThat(sentences.get(i).getId(), is("div" + i));
			assertThat(sentences.get(i).getCss(), is(Const.CSS[i % Const.CSS.length]));
			assertThat(sentences.get(i).getAnswer(), is("answer1"));
			assertThat(sentences.get(i).getHint(), is("hint1"));
		}
	}

	@Test
	public void getSentence() {
		// Given
		String quiz = "# title0\nthis is first\nthis is second\n# title1\nthis is first\nthis is second.";
		article.setContent(quiz);

		// When
		article.makeParagraphs();

		// Then
		assertThat(article.getParagraphs().size(), is(2));
		List<Paragraph> sentences = article.getParagraphs();
		for (int i = 0; i < article.getParagraphs().size(); i++) {
			assertThat(sentences.get(i).getSubtitle(), is("title" + i));
			assertThat(sentences.get(i).getContent(), containsString("this is first"));
			assertThat(sentences.get(i).getId(), is("div" + i));
			assertThat(sentences.get(i).getCss(), is(Const.CSS[i % Const.CSS.length]));
			assertThat(sentences.get(i).getAnswer(), is(StringUtils.EMPTY));
			assertThat(sentences.get(i).getHint(), is(StringUtils.EMPTY));
		}
	}
}