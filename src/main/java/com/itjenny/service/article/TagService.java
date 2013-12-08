package com.itjenny.service.article;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.google.common.collect.Lists;
import com.itjenny.domain.Article;
import com.itjenny.domain.Tag;
import com.itjenny.repository.ArticleRepository;
import com.itjenny.repository.TagRepository;
import com.itjenny.support.security.SessionService;

@Service
public class TagService {
    private final Logger logger = LoggerFactory.getLogger(TagService.class);

    @Autowired
    private SessionService sessionService;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    public List<String> getTags() {
        List<String> tags = tagRepository.findTags();
        return tags;
    }

    public List<String> getTags(String article) {
        List<String> tags = tagRepository.findByArticle(article);
        return tags;
    }

    public List<String> getArticles(String[] tags) {
        if (ObjectUtils.isEmpty(tags)) {
            return Lists.newArrayList();
        }
        List<String> asList = Arrays.asList(tags);
        List<String> titles = tagRepository.findSomeByTag(asList);
        if (titles.isEmpty()) {
            return Lists.newArrayList();
        }
        List<Article> articles = articleRepository.findSome(titles);
        List<String> articlesInTag = Lists.newArrayList();
        for (Article article : articles) {
            if (article.getPublished()
                    || article.getUserId().equals(
                            sessionService.getLoginUser().getUserId())) {
                articlesInTag.add(article.getTitle());
            }
        }
        return articlesInTag;
    }
}