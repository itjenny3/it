package com.itjenny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.itjenny.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, String> {
    @Query("select distinct tag from Tag")
	List<String> findTags();
	
    @Query("select article from Tag t where t.tag = :tag")
	List<String> findByTag(@Param("tag") String tag);
	
    @Query("select tag from Tag t where t.article = :article")
	List<String> findByArticle(@Param("article") String article);
}
