package com.itjenny.service.article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.domain.Article;
import com.itjenny.domain.Chapter;
import com.itjenny.domain.HtmlArticle;
import com.itjenny.support.Const;

@Service
public class HtmlArticleService {
    private final Logger logger = LoggerFactory
            .getLogger(HtmlArticleService.class);

    @Autowired
    private ArticleService articleService;

    private Map<String, HtmlArticle> htmlArticles = new HashMap<String, HtmlArticle>();
    private PegDownProcessor pegDownProcessor = new PegDownProcessor(
            Extensions.ALL);

    public int getTotalSection(String title) {
        int totalSection = 0;
        for (Chapter chapter : htmlArticles.get(title).getChapters()) {
            totalSection += chapter.getSections().size();
            if (chapter.getQuiz() != null) {
                totalSection++;
            }
        }
        return totalSection;
    }

    public HtmlArticle getArticle(String title) {
        HtmlArticle htmlArticle = htmlArticles.get(title);
        if (htmlArticle == null) {
            Article article = articleService.get(title);
            if (article == null) {
                return null;
            }
            htmlArticles.put(title, new HtmlArticle(pegDownProcessor, title,
                    article.getContent()));
        }
        return htmlArticles.get(title);
    }

    public List<Chapter> getChaptersToIndex(String title, Integer toIndex) {
        HtmlArticle htmlArticle = getArticle(title);
        if (htmlArticle == null) {
            return null;
        }
        if (toIndex.equals(Const.BOOKMARK_LICENSE)) {
            return htmlArticle.getChapters();
        }
        return htmlArticle.getChapters().subList(0, toIndex + 1);
    }

    public Chapter getChapter(String title, int index) {
        HtmlArticle htmlArticle = getArticle(title);
        if (htmlArticle == null) {
            return null;
        }
        return htmlArticle.getChapters().get(index);
    }

    public boolean isChapterExisted(String title, int index) {
        HtmlArticle htmlArticle = getArticle(title);
        if (htmlArticle == null) {
            return false;
        }
        if (htmlArticle.getChapters().size() <= index) {
            return false;
        }
        return true;
    }
}