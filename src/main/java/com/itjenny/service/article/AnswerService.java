package com.itjenny.service.article;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.domain.Chapter;

@Service
public class AnswerService {
	private final Logger logger = LoggerFactory.getLogger(AnswerService.class);

	@Autowired
	private HtmlArticleService htmlArticleService;

	public boolean check(Chapter chapter, String answer) {
		if (trim(chapter.getQuiz().getAnswer()).equals(trim(answer))) {
			return true;
		}
		return false;
	}

	public String trim(String answer) {
		answer = answer.replace(" ", "");
		answer = answer.toLowerCase();
		return answer;
	}
}