package com.itjenny.service.article;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.domain.Article;
import com.itjenny.repository.ArticleRepository;
import com.itjenny.support.security.SessionService;

@Service
public class ArticleService {
	private final Logger logger = LoggerFactory.getLogger(ArticleService.class);

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ArticleRepository articleRepository;

	public void save(Article article) {
		articleRepository.save(article);
	}

	public List<Article> getAll() {
		return articleRepository.findAll(sessionService.getLoginUser().getUserId());
	}

	public Article get(String title) {
		Article article = null;
		if (articleRepository.exists(title)) {
			article = articleRepository.findOne(title, sessionService.getLoginUser().getUserId());
		}
		return article;
	}
}