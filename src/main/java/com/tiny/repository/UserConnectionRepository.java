package com.tiny.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiny.dao.UserConnectionDao;

@Repository
public class UserConnectionRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserConnectionRepository.class);

	@Autowired
	private UserConnectionDao userConnectionDao;

	public String getProviderUserId(String userId) {
		return userConnectionDao.getProviderUserId(userId);
	}
}
