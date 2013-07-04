package com.tiny.controller;

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

import com.tiny.common.util.Constant;
import com.tiny.model.Article;
import com.tiny.service.ArticleService;

@Controller
public class ArticleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = Constant.ARTICLE + "/{title}", method = RequestMethod.GET)
	public ModelAndView get(@PathVariable String title) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		Article article = articleService.get(title);
		article.setHtml(articleService.convertToHtml(article.getContent()));
		model.addAttribute("article", article);
		mav.setViewName("article");
		mav.addAllObjects(model);
		return mav;
	}
	
	@RequestMapping(value = Constant.ARTICLE + "/{title}", method = RequestMethod.POST)
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
}