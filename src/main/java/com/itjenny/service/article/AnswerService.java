package com.itjenny.service.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.domain.Chapter;

@Service
public class AnswerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AnswerService.class);

	@Autowired
	private HtmlArticleService htmlArticleService;

	public boolean check(Chapter chapter, String answer) {
		if (chapter.getQuiz().getAnswer().equals(answer)) {
			return true;
		}
		return false;
	}
}