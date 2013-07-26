package com.itjenny.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnswerService.class);

	@Autowired
	HtmlArticleService htmlArticleService;
	
	public boolean check(String title, String id, String answer) {
		return true;
	}
}