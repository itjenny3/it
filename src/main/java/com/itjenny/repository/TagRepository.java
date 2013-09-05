package com.itjenny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itjenny.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, String> {
	List<Tag> findByTag(String tag);
	List<Tag> findByArticle(String article);
}
