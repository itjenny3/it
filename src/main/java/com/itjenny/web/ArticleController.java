package com.itjenny.web;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.view.RedirectView;

import com.itjenny.domain.Article;
import com.itjenny.domain.Chapter;
import com.itjenny.service.SettingService;
import com.itjenny.service.ThemeService;
import com.itjenny.service.article.AnswerService;
import com.itjenny.service.article.ArticleService;
import com.itjenny.service.article.BookmarkService;
import com.itjenny.service.article.TagService;
import com.itjenny.support.Consts;
import com.itjenny.support.URL;
import com.itjenny.support.View;
import com.itjenny.support.security.SessionService;

@Controller
@RequestMapping(value = { URL.ARTICLE })
public class ArticleController {
    private final Logger logger = LoggerFactory
            .getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ThemeService themeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private BookmarkService bookmarkService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SettingService settingService;

    @RequestMapping(value = "{title}", method = RequestMethod.POST)
    public ModelAndView save(@PathVariable String title,
            @RequestParam String content) {
        ModelAndView mav = new ModelAndView();
        ModelMap model = new ModelMap();
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setUserId(StringUtils.EMPTY);
        article.setPublished(true);
        articleService.save(article);
        mav.setViewName(View.ARTICLE);
        mav.addAllObjects(model);
        return new ModelAndView(new RedirectView(URL.makeAbsolutePath(
                URL.ARTICLE, title)));
    }

    @RequestMapping(value = "{title}/{chapterCssId}", method = RequestMethod.POST)
    public ModelAndView checkAnswer(HttpServletRequest request,
            @PathVariable String title, @PathVariable String chapterCssId,
            @RequestParam String answer) {
        Integer chapterIndex = Integer.valueOf(chapterCssId.replace(
                Consts.CHAPTER, StringUtils.EMPTY));
        ModelAndView mav = new ModelAndView();
        ModelMap model = new ModelMap();
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("keynote")) {
                Article article = articleService.get(title);
                if (article == null
                        || !article.getUserId().equals(
                                sessionService.getLoginUser().getUserId())) {
                    break;
                }

                // keynote mode
                if (!articleService.isChapterExisted(title, chapterIndex + 1)) {
                    bookmarkService.complete(title);
                    return new ModelAndView("redirect:/article/" + title
                            + "/license");
                }
                model.addAttribute("chapter",
                        articleService.getChapter(title, chapterIndex + 1));
                model.addAttribute("totalSection",
                        articleService.getTotalSection(title));
                model.addAttribute("answer",
                        articleService.getChapter(title, chapterIndex)
                                .getQuiz().getAnswer());
                mav.setViewName(View.CHAPTER);
                mav.addAllObjects(model);

                bookmarkService.updateChapter(title, chapterIndex + 1);
                return mav;
            }
        }

        // word mode
        Chapter chapter = articleService.getChapter(title, chapterIndex);
        if (answerService.check(chapter, answer)) {
            if (!articleService.isChapterExisted(title, chapterIndex + 1)) {
                bookmarkService.complete(title);
                return new ModelAndView("redirect:/article/" + title
                        + "/license");
            }
            model.addAttribute("chapter",
                    articleService.getChapter(title, chapterIndex + 1));
            model.addAttribute("totalSection",
                    articleService.getTotalSection(title));
            model.addAttribute("answer",
                    articleService.getChapter(title, chapterIndex).getQuiz()
                            .getAnswer());
            mav.setViewName(View.CHAPTER);
            mav.addAllObjects(model);

            bookmarkService.updateChapter(title, chapterIndex + 1);
        } else {
            mav.setViewName(View.WRONG);
            mav.addAllObjects(model);
        }
        return mav;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        ModelMap model = new ModelMap();
        List<Article> articles = articleService.getAll();
        List<String> tags = tagService.getTags();
        model.addAttribute("articles", articles);
        model.addAttribute("tags", tags);
        mav.setViewName(View.ARTICLES);
        mav.addAllObjects(model);
        return mav;
    }

    @RequestMapping(value = "{title}", method = RequestMethod.GET)
    public ModelAndView getArticle(@PathVariable String title) {
        ModelAndView mav = new ModelAndView();
        ModelMap model = new ModelMap();
        Integer chapterIndex = bookmarkService.getChapterIndex(title);
        List<Chapter> chapters = articleService.getChaptersToIndex(title,
                chapterIndex);
        if (chapters == null) {
            logger.info("title({}) isn't existed", title);
            return new ModelAndView("redirect:/" + URL.ARTICLE);
        }
        model.addAttribute("title", title);
        model.addAttribute("chapters", chapters);
        model.addAttribute("license",
                chapterIndex.equals(Consts.BOOKMARK_LICENSE));
        model.addAttribute("totalSection",
                articleService.getTotalSection(title));
        model.addAttribute("setting",
                settingService.get(sessionService.getLoginUser().getUserId()));
        String css = articleService.get(title).getCss();
        if (StringUtils.isEmpty(css)) {
            model.addAttribute("css", themeService.getDefault().getCss());
        } else {
            model.addAttribute("css", css);
        }

        mav.setViewName(View.ARTICLE);
        mav.addAllObjects(model);
        bookmarkService.updateChapter(title, 0);
        return mav;
    }

    @RequestMapping(value = "{title}/license", method = RequestMethod.GET)
    public ModelAndView license(@PathVariable String title) {
        ModelAndView mav = new ModelAndView();
        ModelMap model = new ModelMap();
        model.addAttribute("title", title);
        mav.setViewName(View.LICENSE);
        mav.addAllObjects(model);
        return mav;
    }
}