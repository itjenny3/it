package com.itjenny.service.article;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itjenny.common.CommonTest;
import com.itjenny.domain.Article;
import com.itjenny.domain.Tag;
import com.itjenny.repository.ArticleRepository;
import com.itjenny.repository.TagRepository;

public class ArticleRepositoryTest extends CommonTest {
	@Autowired
	ArticleRepository articleRepository;

	private static final String TITLE1 = "QA";
	private static final String TITLE2 = "private";
	private static final String CONTENT1 = "Skill Set0\n---------\n - Spring : 자바 Framework\n\nSkill Set\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer\n\nSkill Set2\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer2\n\nSkill Set3\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer3\n\n";
	private static final String CONTENT2 = "private";
	private static final String USERID1 = "itjenny";

	@Test
	public void setInit() {
		// Given
		Article article = new Article();
		article.setTitle(TITLE1);
		article.setContent(CONTENT1);
		article.setPublished(true);
		article.setUserId(USERID1);
		articleRepository.save(article);
		
		article.setTitle(TITLE2);
		article.setContent(CONTENT2);
		article.setPublished(false);
		article.setUserId(USERID1);
		articleRepository.save(article);
	}
}