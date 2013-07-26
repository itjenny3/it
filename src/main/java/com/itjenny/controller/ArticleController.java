package com.itjenny.controller;

import java.util.List;

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

import com.itjenny.common.util.Const;
import com.itjenny.model.Article;
import com.itjenny.model.HtmlArticle;
import com.itjenny.service.AnswerService;
import com.itjenny.service.ArticleService;
import com.itjenny.service.HtmlArticleService;

@Controller
public class ArticleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private HtmlArticleService htmlArticleService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private AnswerService answerService;

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
		HtmlArticle htmlArticle = htmlArticleService.get(title);
		if (htmlArticle == null) {
			return new ModelAndView("redirect:/article");
		}
		model.addAttribute("htmlArticle", htmlArticle);
		mav.setViewName("article");
		mav.addAllObjects(model);
		return mav;
	}

	@RequestMapping(value = Const.ARTICLE + "/{title}/{chapter}", method = RequestMethod.POST)
	public ModelAndView answer(@PathVariable String title, @PathVariable String chapter, @RequestParam String answer) {
		answerService.check(title, chapter, answer);

		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		HtmlArticle htmlArticle = htmlArticleService.get(title);
		if (htmlArticle == null) {
			return new ModelAndView("redirect:/article");
		}
		model.addAttribute("htmlArticle", htmlArticle);
		mav.setViewName("chapter");
		mav.addAllObjects(model);
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