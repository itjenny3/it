package com.itjenny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itjenny.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, String> {
    @Query("select a from Article a where a.userId = :userId OR a.published = 1")
    List<Article> findAll(@Param("userId") String userId);

    @Query("select a from Article a where a.title in (:titles)")
    List<Article> findSome(@Param("titles") List<String> titles);

    @Query("select a from Article a where a.title = :title AND (a.userId = :userId OR a.published = 1)")
    Article findOne(@Param("title") String title, @Param("userId") String userId);
}