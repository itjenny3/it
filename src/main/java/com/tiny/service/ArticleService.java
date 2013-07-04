package com.tiny.service;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.markdown4j.Markdown4jProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiny.common.util.Constants;
import com.tiny.common.util.XssFilter;
import com.tiny.model.Article;
import com.tiny.repository.ArticleRepository;
import com.tiny.social.SecurityContext;

@Service
public class ArticleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

	@Autowired
	private XssFilter xssFilter;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private SecurityContext securityContext;

	public void save(Article article) {
		articleRepository.save(article);
	}

	public Article get(String title) {
		return articleRepository.get(title);
	}

	public void delete(String title) {
		articleRepository.delete(title);
	}

	public String convertToHtml(String markdown) {
		StringBuilder html = new StringBuilder();
		try {
			if (StringUtils.isNotEmpty(markdown)) {
				String[] paragraphs = new Markdown4jProcessor().process(markdown).split("(?=(<h1>|<h2>))");
				for (int i = 0; i < paragraphs.length; i++) {
					html.append("<div class=").append(Constants.CSSLIST[i % Constants.CSSLIST.length]).append(">")
							.append(paragraphs[i]).append("</div>");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html.toString();
	}
}