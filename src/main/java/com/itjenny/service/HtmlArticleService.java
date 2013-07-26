package com.itjenny.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.model.Article;
import com.itjenny.model.HtmlArticle;

@Service
public class HtmlArticleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlArticleService.class);

	@Autowired
	ArticleService articleService;

	Map<String, HtmlArticle> htmlArticles = new HashMap<String, HtmlArticle>();

	public HtmlArticle get(String title) {
		HtmlArticle htmlArticle = htmlArticles.get(title);
		if (htmlArticle == null) {
			Article article = articleService.get(title);
			if (article != null) {
				htmlArticles.put(title, new HtmlArticle(title, article.getContent()));
			}
		}
		return htmlArticle;
	}
}