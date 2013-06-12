package com.tiny.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiny.dao.ArticleDao;
import com.tiny.model.Article;

@Repository
public class ArticleRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleRepository.class);

	@Autowired
	private ArticleDao articleDao;

	public void save(Article article) {
		articleDao.save(article);
	}

	public List<Article> getAll() {
		return articleDao.getAll();
	}
	
	public Article get(String title) {
		return articleDao.get(title);
	}
	
	public void delete(String title) {
		articleDao.delete(title);
	}
}
