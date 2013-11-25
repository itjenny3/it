package com.itjenny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itjenny.domain.Bookmark;
import com.itjenny.service.article.BookmarkPK;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    @Query("select a from Bookmark a where a.title = :title AND a.userId = :userId")
    Bookmark findOne(@Param("title") String title, @Param("userId") String userId);
}
