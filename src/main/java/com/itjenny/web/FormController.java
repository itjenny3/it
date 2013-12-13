package com.itjenny.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.common.base.Strings;
import com.itjenny.domain.Article;
import com.itjenny.service.article.ArticleService;
import com.itjenny.support.URL;
import com.itjenny.support.VIEW;

@Controller
@RequestMapping(value = { URL.FORM })
public class FormController {
    private final Logger logger = LoggerFactory.getLogger(FormController.class);

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = URL.NEW, method = RequestMethod.POST)
    public ModelAndView save(String title, String content) {
        ModelAndView mav = new ModelAndView();
        ModelMap model = new ModelMap();
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setUserId(StringUtils.EMPTY);
        article.setPublished(true);
        articleService.save(article);
        mav.setViewName(VIEW.ARTICLE);
        mav.addAllObjects(model);
		return new ModelAndView(new RedirectView(URL.makeAbsolutePath(URL.ARTICLE, title)));
    }

    @RequestMapping(value = URL.ARTICLE, method = RequestMethod.GET)
    public ModelAndView form(@RequestParam(required = false) String title) {
        ModelAndView mav = new ModelAndView();
        ModelMap model = new ModelMap();
        Article article;
        if (!Strings.isNullOrEmpty(title)) {
            article = articleService.get(title);
        } else {
            article = new Article();
        }
        model.addAttribute("article", article);
        mav.setViewName(VIEW.ARTICLE_FORM);
        mav.addAllObjects(model);
        return mav;
    }
}