package com.itjenny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itjenny.model.Article;

public interface ArticleRepository extends JpaRepository<Article, String> {
}
