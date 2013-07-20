package com.itjenny.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.markdown4j.Markdown4jProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.common.util.Constant;
import com.itjenny.model.Article;
import com.itjenny.repository.ArticleRepository;

@Service
public class ArticleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

	@Autowired
	private ArticleRepository articleRepository;

	public void save(Article article) {
		articleRepository.save(article);
	}

	public List<Article> getAll() {
		return articleRepository.findAll();
	}

	public Article get(String title) {
		Article article = null;
		if (articleRepository.exists(title)) {
			article = articleRepository.findOne(title);
		}
		return article;
	}

	public void delete(String title) {
		articleRepository.delete(title);
	}

	public String convertToHtml(String markdown) {
		StringBuilder html = new StringBuilder();
		try {
			if (StringUtils.isNotEmpty(markdown)) {
				int i = 0;
				String[] paragraphs = new Markdown4jProcessor().process(markdown).split("(?=(<h1>|<h2>))");
				for (String paragraph : paragraphs) {
					if (!"".equals(paragraph)) {
						html.append("<div class=").append(Constant.CSSLIST[(i++) % Constant.CSSLIST.length])
								.append(">").append(paragraph).append("</div>");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html.toString();
	}
}