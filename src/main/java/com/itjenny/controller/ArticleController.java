package com.itjenny.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itjenny.domain.Article;
import com.itjenny.domain.Chapter;
import com.itjenny.service.AuthService;
import com.itjenny.service.BookmarkService;
import com.itjenny.service.article.AnswerService;
import com.itjenny.service.article.ArticleService;
import com.itjenny.service.article.HtmlArticleService;
import com.itjenny.support.Const;
import com.itjenny.support.URL;
import com.itjenny.support.VIEW;
import com.itjenny.support.security.SessionService;

@Controller
public class ArticleController {
	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private HtmlArticleService htmlArticleService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private BookmarkService bookmarkService;

	@Autowired
	private AuthService authService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private SessionService sessionService;

	@RequestMapping(value = URL.ARTICLE + "/{title}", method = RequestMethod.POST)
	public ModelAndView save(@PathVariable String title, @RequestParam String content) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		articleService.save(article);
		mav.setViewName(VIEW.ARTICLE);
		mav.addAllObjects(model);
		return mav;
	}

	@RequestMapping(value = StringUtils.EMPTY, method = RequestMethod.GET)
	public ModelAndView listTemp() {
		return list();
	}

	@RequestMapping(value = URL.ARTICLE, method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		List<Article> articles = articleService.getAll();
		model.addAttribute("articles", articles);
		mav.setViewName(VIEW.ARTICLES);
		mav.addAllObjects(model);
		return mav;
	}

	@RequestMapping(value = URL.ARTICLE + "/{title}", method = RequestMethod.GET)
	public ModelAndView getArticle(@PathVariable String title) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		List<Chapter> chapters = htmlArticleService.getToChapter(title, authService.getPage("loginId"));
		if (chapters == null) {
			logger.info("title isn't existed");
			return new ModelAndView("redirect:/article");
		}
		model.addAttribute("title", title);
		model.addAttribute("chapters", chapters);
		mav.setViewName(VIEW.ARTICLE);
		mav.addAllObjects(model);
		bookmarkService.updateChapter(title, 0);
		return mav;
	}

	@RequestMapping(value = URL.ARTICLE + "/{title}/{chapterId}", method = RequestMethod.POST)
	public ModelAndView answer(@PathVariable String title, @PathVariable String chapterId, @RequestParam String answer) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		Integer chapterIndex = Integer.valueOf(chapterId.replace(Const.CHAPTER, StringUtils.EMPTY));
		Chapter chapter = htmlArticleService.getChapter(title, chapterIndex);
		if (answerService.check(chapter, answer)) {
			if (htmlArticleService.isChapterExisted(title, chapterIndex + 1) == false) {
				bookmarkService.complete(title);
				return new ModelAndView("redirect:/article/" + title + "/license");
			}
			model.addAttribute("chapter", htmlArticleService.getChapter(title, chapterIndex + 1));
			mav.setViewName(VIEW.CHAPTER);
			mav.addAllObjects(model);
			bookmarkService.updateChapter(title, chapterIndex + 1);
		} else {
			mav.setViewName(VIEW.WRONG);
			mav.addAllObjects(model);
		}
		return mav;
	}

	@RequestMapping(value = URL.ARTICLE + "/{title}/license", method = RequestMethod.GET)
	public ModelAndView completed(@PathVariable String title) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		model.addAttribute("title", title);
		mav.setViewName(VIEW.LICENSE);
		mav.addAllObjects(model);
		return mav;
	}
}