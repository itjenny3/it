package com.itjenny.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.itjenny.service.article.AnswerService;
import com.itjenny.service.article.ArticleService;
import com.itjenny.service.article.BookmarkService;
import com.itjenny.service.article.HtmlArticleService;
import com.itjenny.support.Const;
import com.itjenny.support.URL;
import com.itjenny.support.VIEW;
import com.itjenny.support.security.SessionService;

@Controller
@RequestMapping(value = { "", URL.ARTICLE })
public class ArticleController {
	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private HtmlArticleService htmlArticleService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private BookmarkService bookmarkService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private SessionService sessionService;

	@RequestMapping(value = "{title}", method = RequestMethod.POST)
	public ModelAndView save(@PathVariable String title, @RequestParam String content) {
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
		return mav;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		List<Article> articles = articleService.getAll();
		model.addAttribute("articles", articles);
		mav.setViewName(VIEW.ARTICLES);
		mav.addAllObjects(model);
		return mav;
	}

	@RequestMapping(value = "{title}", method = RequestMethod.GET)
	public ModelAndView getArticle(@PathVariable String title) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		Integer chapterIndex = bookmarkService.getChapterIndex(title);
		List<Chapter> chapters = htmlArticleService.getChaptersToIndex(title, chapterIndex);
		if (chapters == null) {
			logger.info("title({}) isn't existed", title);
			return new ModelAndView("redirect:/" + URL.ARTICLE);
		}
		model.addAttribute("title", title);
		model.addAttribute("chapters", chapters);
		model.addAttribute("license", (chapterIndex.equals(Const.BOOKMARK_LICENSE)));
		model.addAttribute("loginUserId", sessionService.getLoginUser().getUserId());
		model.addAttribute("totalSection", htmlArticleService.geTotalSection(title));
		mav.setViewName(VIEW.ARTICLE);
		mav.addAllObjects(model);
		bookmarkService.updateChapter(title, 0);
		return mav;
	}

	@RequestMapping(value = "{title}/{chapterCssId}", method = RequestMethod.POST)
	public ModelAndView answer(@PathVariable String title, @PathVariable String chapterCssId,
			@RequestParam String answer) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		Integer chapterIndex = Integer.valueOf(chapterCssId.replace(Const.CHAPTER, StringUtils.EMPTY));
		Chapter chapter = htmlArticleService.getChapter(title, chapterIndex);
		if (answerService.check(chapter, answer)) {
			if (!htmlArticleService.isChapterExisted(title, chapterIndex + 1)) {
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

	@RequestMapping(value = "{title}/license", method = RequestMethod.GET)
	public ModelAndView license(@PathVariable String title) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		model.addAttribute("title", title);
		model.addAttribute("loginUserId", sessionService.getLoginUser().getUserId());
		mav.setViewName(VIEW.LICENSE);
		mav.addAllObjects(model);
		return mav;
	}
}