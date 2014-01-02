package com.itjenny.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itjenny.service.article.ArticleService;
import com.itjenny.service.article.TagService;
import com.itjenny.support.URL;
import com.itjenny.support.View;

@Controller
@RequestMapping(URL.SEARCH)
public class SearchController {
    private final Logger logger = LoggerFactory
            .getLogger(SearchController.class);

    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView searchArticles(@RequestParam(required = false) String q) {
        ModelAndView mav = new ModelAndView();
        ModelMap model = new ModelMap();
        String removedQuotationAndSpace = StringUtils
                .replaceChars(q, "\" ", "");
        List<String> articlesInTag = tagService.getArticles(StringUtils.split(
                removedQuotationAndSpace, ","));
        List<String> articlesWithKeyword = articleService.getArticlesWithKeyword(q);
        model.addAttribute("q", q);
        model.addAttribute("articlesInTag", articlesInTag);
        model.addAttribute("articlesWithKeyword", articlesWithKeyword);
        mav.setViewName(View.SEARCH);
        mav.addAllObjects(model);
        return mav;
    }
}