package com.itjenny.service.article;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itjenny.domain.Article;
import com.itjenny.repository.ArticleRepository;

@Service
public class ArticleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

    @Resource(name = "articleRepository")
	private ArticleRepository articleRepository;
	
	public void save(Article article) {
		articleRepository.save(article);
	}

	public List<Article> getAll() {
		return articleRepository.findAll();
	}

	public Article get(String title) {
		Article article = null;
		if (articleRepository.exists(title)) {
			article = articleRepository.findOne(title);
		}
		return article;
	}
}