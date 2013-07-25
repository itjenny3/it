package com.itjenny.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.markdown4j.Markdown4jProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.common.util.Const;
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

	public void makeTitle(StringBuilder html, String title) {
		html.append("<div class=backgroundTitle><a href=#div1 class=anchorLink><h1>").append(title)
				.append("</h1></a></div>");
	}

	public void makeSubtitle(StringBuilder html, String title, int i, int length) {
		if (i == length) {
			html.append("<a href=#license class=anchorLink><h1>").append(title).append("</h1></a>");
		} else {
			html.append("<a href=#div").append(i + 1).append(" class=anchorLink><h1>").append(title)
					.append("</h1></a>");
		}
	}
	

	public String convertToHtml(String title, String markdown) {
		StringBuilder html = new StringBuilder();
		makeTitle(html, title);
		try {
			if (StringUtils.isNotEmpty(markdown)) {
				String[] paragraphs = new Markdown4jProcessor().process(markdown).split("<h1>|<h2>");
				for (int i = 0; i < paragraphs.length; i++) {
					if (!"".equals(paragraphs[i])) {
						html.append("<div id=div").append(i).append(" class=")
								.append(Const.CSS[i % Const.CSS.length]).append(">");
						String[] split = paragraphs[i].split("</h1>|</h2>");
						if (split.length == 2) {
							makeSubtitle(html, split[0], i, paragraphs.length - 1);
							html.append(split[1]).append("</div>");
                    		if (split[0].equalsIgnoreCase(Const.QUIZ)) {
                    		}
						} else {
							// no title
							html.append(split[0]).append("</div>");
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html.toString();
	}
}