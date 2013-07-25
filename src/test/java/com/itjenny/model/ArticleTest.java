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
	public void quiz() {
		// Given
		String quiz = "# quiz\nthis is question\nAnswer> answer2\nHint> this is hint.";
		article.setContent(quiz);

		// When
		article.setSentence();

		// Then
		assertThat(article.getSentences().size(), is(1));
		List<Sentence> sentences = article.getSentences();
		for (int i = 0; i < article.getSentences().size(); i++) {
			assertThat(sentences.get(i).getSubtitle(), is("quiz"));
			assertThat(sentences.get(i).getContent(), containsString("this is question"));
			assertThat(sentences.get(i).getId(), is("div" + i));
			assertThat(sentences.get(i).getCss(), is(Const.CSS[i % Const.CSS.length]));
			assertThat(sentences.get(i).getAnswer(), containsString("answer2"));
			assertThat(sentences.get(i).getHint(), containsString("this is hint."));
		}
	}

	@Test
	public void getSentence() {
		// Given
		String quiz = "# title0\nthis is first\nthis is second\n# title1\nthis is first\nthis is second.";
		article.setContent(quiz);

		// When
		article.setSentence();

		// Then
		assertThat(article.getSentences().size(), is(2));
		List<Sentence> sentences = article.getSentences();
		for (int i = 0; i < article.getSentences().size(); i++) {
			assertThat(sentences.get(i).getSubtitle(), is("title" + i));
			assertThat(sentences.get(i).getContent(), containsString("this is first"));
			assertThat(sentences.get(i).getId(), is("div" + i));
			assertThat(sentences.get(i).getCss(), is(Const.CSS[i % Const.CSS.length]));
			assertThat(sentences.get(i).getAnswer(), is(StringUtils.EMPTY));
			assertThat(sentences.get(i).getHint(), is(StringUtils.EMPTY));
		}
	}
}