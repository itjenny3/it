package com.itjenny.service.tag;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itjenny.common.CommonTest;
import com.itjenny.domain.Tag;
import com.itjenny.repository.TagRepository;

public class TagServiceTest extends CommonTest {
	@Autowired
	private TagRepository tagRepository;
	
	private static final String TAG1 = "TAG1";
	private static final String ARTICLE1 = "ARTICLE";
	private static final String ARTICLE2 = "ARTICLE2";
	
	@Test
	public void getTags() {
		// Given
		int savedSize = tagRepository.findByTag(TAG1).size();
		Tag tag = new Tag();
		tag.setTag(TAG1);
		tag.setArticle(ARTICLE1);
		tagRepository.save(tag);
		tag.setArticle(ARTICLE2);
		tagRepository.save(tag);
		
		// When
		List<String> articles = tagRepository.findByTag(TAG1);
		
		// Then
		assertThat(articles.size() - savedSize, is(2));
	}
}