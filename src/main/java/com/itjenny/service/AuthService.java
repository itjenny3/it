package com.itjenny.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthService.class);

	public Integer getPage(String loginId) {
		return 1;
	}
}