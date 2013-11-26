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

import com.itjenny.service.article.TagService;
import com.itjenny.support.URL;
import com.itjenny.support.VIEW;

@Controller
@RequestMapping(URL.TAG)
public class TagController {
    private final Logger logger = LoggerFactory.getLogger(TagController.class);

    @Autowired
    TagService tagService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getArticlesInTags(
            @RequestParam(required = false) String tags) {
        ModelAndView mav = new ModelAndView();
        ModelMap model = new ModelMap();
        String removedQuotation = StringUtils.replace(tags, "\"", "");
        List<String> articles = tagService.getArticles(StringUtils.split(
                removedQuotation, ","));
        model.addAttribute("articles", articles);
        mav.setViewName(VIEW.TAG);
        mav.addAllObjects(model);
        return mav;
    }
}