package com.tiny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiny.model.Article;

public interface ArticleRepository extends JpaRepository<Article, String> {
}
