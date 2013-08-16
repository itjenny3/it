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
import com.itjenny.service.article.AnswerService;
import com.itjenny.service.article.ArticleService;
import com.itjenny.service.article.HtmlArticleService;
import com.itjenny.support.Const;
import com.itjenny.support.security.SessionService;

@Controller
public class ArticleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private HtmlArticleService htmlArticleService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private AuthService authService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private SessionService sessionService;

	@RequestMapping(value = Const.ARTICLE + "/{title}", method = RequestMethod.POST)
	public ModelAndView save(@PathVariable String title, @RequestParam String content) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		articleService.save(article);
		mav.setViewName("article");
		mav.addAllObjects(model);
		return mav;
	}
	
	@RequestMapping(value = StringUtils.EMPTY, method = RequestMethod.GET)
	public ModelAndView listTemp() {
		return list();
	}

	@RequestMapping(value = Const.ARTICLE, method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		List<Article> articles = articleService.getAll();
		model.addAttribute("articles", articles);
		mav.setViewName("articles");
		mav.addAllObjects(model);
		return mav;
	}

	@RequestMapping(value = Const.ARTICLE + "/{title}", method = RequestMethod.GET)
	public ModelAndView getArticle(@PathVariable String title) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		List<Chapter> chapters = htmlArticleService.getToChapter(title, authService.getPage("loginId"));
		if (chapters == null) {
			LOGGER.info("title isn't existed");
			return new ModelAndView("redirect:/article");
		}
		model.addAttribute("title", title);
		model.addAttribute("chapters", chapters);
		mav.setViewName("article");
		mav.addAllObjects(model);
		return mav;
	}

	@RequestMapping(value = Const.ARTICLE + "/{title}/{chapterId}", method = RequestMethod.POST)
	public ModelAndView answer(@PathVariable String title, @PathVariable String chapterId, @RequestParam String answer) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		Chapter chapter = htmlArticleService.getChapter(title, Integer.parseInt(chapterId.replace(Const.CHAPTER, "")));
		if (answerService.check(chapter, answer)) {
			model.addAttribute("chapter", chapter);
			mav.setViewName("chapter");
			mav.addAllObjects(model);
		} else {
			mav.setViewName("wrong");
			mav.addAllObjects(model);
		}
		return mav;
	}

	@RequestMapping(value = Const.ARTICLE + "/{title}/license", method = RequestMethod.GET)
	public ModelAndView completed(@PathVariable String title, @RequestParam String id) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		model.addAttribute("title", title);
		model.addAttribute("id", id);
		mav.setViewName("license");
		mav.addAllObjects(model);
		return mav;
	}
}