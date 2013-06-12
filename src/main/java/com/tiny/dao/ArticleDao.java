package com.tiny.dao;

import java.util.List;

import com.tiny.model.Article;

public interface ArticleDao {
	public void save(Article article);
	public List<Article> getAll();
	public Article get(String title);
	public void delete(String title);
}