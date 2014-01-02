package com.itjenny.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.pegdown.PegDownProcessor;

import com.itjenny.support.Consts;

@Data
public class HtmlArticle {
    private static final String H1 = "<h1>";
    private static final String H2 = "<h2>";
    private static final String H1H2 = "<h1>|<h2>";
    private static final String H1END = "</h1>";
    private static final String H2END = "</h2>";
    private static final String H1H2END = "</h1>|</h2>";

    private String title;
    private List<Chapter> chapters = new ArrayList<Chapter>();
    private Chapter currentChapter = null;

    public HtmlArticle(PegDownProcessor pegDownProcessor, String title,
            String content) {
        int sectionIndex = 0;
        this.title = title;

        String[] parts = pegDownProcessor.markdownToHtml(content).split(H1H2);
        for (String part : parts) {
            if (StringUtils.isNotEmpty(part)) {
                String[] subtitleAndContent = part.split(H1H2END);
                int subtitleSize;
                if (part.contains(H1END)) {
                    subtitleSize = 1;
                } else {
                    subtitleSize = 2;
                }

                if (subtitleAndContent.length == 2) {
                    // TITLE exists.
                    if (subtitleAndContent[0].equalsIgnoreCase(Consts.QUIZ)) {
                        // QUIZ
                        setQuiz(createQuiz(subtitleAndContent, ++sectionIndex));
                    } else {
                        // SECTION
                        add(createSection(subtitleAndContent[0], subtitleSize,
                                subtitleAndContent[1], ++sectionIndex));
                    }
                } else {
                    if (StringUtils.endsWith(part, H1END)
                            || StringUtils.endsWith(part, H2END)) {
                        // only TITLE
                        add(createSectionWithSubtitle(subtitleAndContent[0],
                                subtitleSize, ++sectionIndex));
                    } else {
                        // only CONTENT
                        add(createSectionWithContent(subtitleAndContent[0],
                                ++sectionIndex));
                    }
                }
            }
        }
        add(currentChapter);
    }

    private Quiz createQuiz(String[] subtitleAndContent, int sectionIndex) {
        Quiz quiz = new Quiz();
        quiz.setIndex(sectionIndex);
        quiz.setSubtitle(subtitleAndContent[0]);
        String[] contentAndAnswer = subtitleAndContent[1]
                .split(Consts.ANSWER_START_TAG);
        switch (contentAndAnswer.length) {
        case 1:
            quiz.setContent(contentAndAnswer[0]);
            break;

        case 2:
            quiz.setContent(contentAndAnswer[0]);
            quiz.setAnswer(contentAndAnswer[1].split(Consts.ANSWER_END_TAG)[0]);
            break;

        default:
            break;
        }
        return quiz;
    }

    private Section createSection(String subtitle, Integer subtitleSize,
            String content, int sectionIndex) {
        Section section = new Section();
        section.setIndex(sectionIndex);
        section.setSubtitle(subtitle);
        section.setSubtitleSize(subtitleSize);
        section.setContent(content);
        return section;
    }

    private Section createSectionWithSubtitle(String subtitle,
            Integer subtitleSize, int sectionIndex) {
        Section section = new Section();
        section.setIndex(sectionIndex);
        section.setSubtitle(subtitle);
        section.setSubtitleSize(subtitleSize);
        return section;
    }

    private Section createSectionWithContent(String content, int sectionIndex) {
        Section section = new Section();
        section.setIndex(sectionIndex);
        section.setContent(content);
        return section;
    }

    private void add(Chapter chapter) {
        if (chapter != null) {
            chapter.setId(Consts.CHAPTER + chapters.size());
            chapters.add(chapter);
        }
    }

    private void add(Section section) {
        currentChapter = ObjectUtils.defaultIfNull(currentChapter,
                new Chapter());
        currentChapter.add(section);
    }

    private void setQuiz(Quiz quiz) {
        currentChapter = ObjectUtils.defaultIfNull(currentChapter,
                new Chapter());
        currentChapter.setQuiz(quiz);
        add(currentChapter);
        currentChapter = null;
    }
}