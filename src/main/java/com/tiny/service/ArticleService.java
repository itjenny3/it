package com.tiny.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiny.common.util.XssFilter;
import com.tiny.model.Article;
import com.tiny.repository.ArticleRepository;
import com.tiny.social.SecurityContext;

@Service
public class ArticleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

	@Autowired
	private XssFilter xssFilter;

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private SecurityContext securityContext;

	public void save(Article article) {
		articleRepository.save(article);
	}

	public Article get(String title) {
		return articleRepository.get(title);
	}
	
	public void delete(String title) {
		articleRepository.delete(title);
	}
}