package com.tiny.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);
	
	@Autowired
	private FacebookService facebookService;

	public void post(String content) {
		facebookService.post(content);
	}
}