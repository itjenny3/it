package com.itjenny.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.model.Article;
import com.itjenny.model.Chapter;
import com.itjenny.model.HtmlArticle;

@Service
public class HtmlArticleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(HtmlArticleService.class);

	@Autowired
	private ArticleService articleService;

	private Map<String, HtmlArticle> htmlArticles = new HashMap<String, HtmlArticle>();

	public HtmlArticle get(String title) {
		HtmlArticle htmlArticle = htmlArticles.get(title);
		if (htmlArticle == null) {
			Article article = articleService.get(title);
			if (article != null) {
				htmlArticles.put(title, new HtmlArticle(title, article.getContent()));
			} else {
				return null;
			}
		}
		return htmlArticles.get(title);
	}

	public List<Chapter> getToChapter(String title, Integer toIndex) {
		HtmlArticle htmlArticle = get(title);
		if (htmlArticle != null) {
			return htmlArticle.getChapters().subList(0, toIndex);
		} else {
			return null;
		}
	}

	public Chapter getChapter(String title, Integer index) {
		HtmlArticle htmlArticle = get(title);
		if (htmlArticle != null) {
			return htmlArticle.getChapters().get(index);
		} else {
			return null;
		}
	}
}