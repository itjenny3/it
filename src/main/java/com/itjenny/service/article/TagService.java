package com.itjenny.service.article;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.domain.Tag;
import com.itjenny.repository.TagRepository;

@Service
public class TagService {
	private final Logger logger = LoggerFactory.getLogger(TagService.class);

	@Autowired
	private TagRepository tagRepository;

	public void save(Tag tag) {
		tagRepository.save(tag);
	}

	public List<Tag> getAll() {
		return tagRepository.findAll();
	}
	
	public List<String> getTags() {
		List<String> tags = tagRepository.findTags();
		return tags;
	}

	public List<String> getTags(String article) {
		List<String> tags = tagRepository.findByArticle(article);
		return tags;
	}

	public List<String> getArticles(String tag) {
		List<String> articles = tagRepository.findByTag(tag);
		return articles;
	}
}