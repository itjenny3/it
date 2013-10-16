package com.itjenny.service.article;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itjenny.common.CommonTest;
import com.itjenny.domain.Article;
import com.itjenny.repository.ArticleRepository;

public class ArticleRepositoryTest extends CommonTest {
	@Autowired
	ArticleRepository articleRepository;

	private static final String TITLE1 = "QA";
	private static final String TITLE2 = "private";
	private static final String TITLE3 = "table";
	private static final String TITLE4 = "table2";
	private static final String CONTENT1 = "Skill Set0\n---------\n - Spring : 자바 Framework\n\nSkill Set\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer\n\nSkill Set2\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer2\n\nSkill Set3\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer3\n\nSkill Set4\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer4\n\nSkill Set5\n---------\n - Spring : Java Framework\n\nquiz\n--------\nquestion\n> answer5\n\n";
	private static final String CONTENT2 = "private";
	private static final String CONTENT3 = "| Tables        | Are           | Cool  |\n| ------------- |:-------------:| -----:|\n | col 3 is      | right-aligned | $1600 |\n | col 2 is      | centered      |   $12 |\n | zebra stripes | are neat      |    $1 |";
	private static final String CONTENT4 = "--------------  ---------\n Right  Left\n 12                  12\n 123                555\n --------------  --------- \n Sum                567";
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
		
		article.setTitle(TITLE3);
		article.setContent(CONTENT3);
		article.setPublished(true);
		article.setUserId(USERID1);
		articleRepository.save(article);
		
		article.setTitle(TITLE4);
		article.setContent(CONTENT4);
		article.setPublished(true);
		article.setUserId(USERID1);
		articleRepository.save(article);
	}
}