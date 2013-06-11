package com.tiny.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiny.common.CommonTest;
import com.tiny.model.Like;

public class LikeDaoTest extends CommonTest {
	@Autowired
	private LikeDao likeDao;
	
	private final Integer documentId = 777;
	private final String providerUserId = "providerUserId";

	@Test
	public void save() {
		// Given
		Integer count = likeDao.count();

		// When
		likeDao.save(createLike());

		// Then
		assertThat(likeDao.count(), is(count + 1));
	}
	
	@Test
	public void getByProviderUserId() {
		// Given
		Like likeTmp = createLike();
		likeDao.save(likeTmp);

		// When
		Like like = likeDao.getByProviderUserId(likeTmp.getProviderUserId()).get(0);

		// Then
		assertThat(like.getProviderUserId(), is(likeTmp.getProviderUserId()));
	}

	@Test
	public void delete() {
		// Given
		Like like = createLike();
		likeDao.save(like);
		Integer count = likeDao.count();

		// When
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("providerUserId", providerUserId);
		map.put("documentId", documentId);
		likeDao.delete(map);

		// Then
		assertThat(likeDao.count(), is(count - 1));
	}

	private Like createLike() {
		Like like = new Like();
		like.setProviderUserId(providerUserId);
		like.setDocumentId(documentId);
		like.setIsLike(true);
		return like;
	}
}
