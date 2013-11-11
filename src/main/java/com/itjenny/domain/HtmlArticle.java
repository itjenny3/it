package com.itjenny.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.pegdown.PegDownProcessor;

import com.itjenny.support.Const;

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
	    // TABLE
	    part = part.replace("<table>",
		    "<table class=\"table table-bordered\">");
	    if (StringUtils.isNotEmpty(part)) {
		String[] subtitleAndContent = part.split(H1H2END);
		if (subtitleAndContent.length == 2) {
		    // TITLE exists.
		    if (subtitleAndContent[0].equalsIgnoreCase(Const.QUIZ)) {
			// QUIZ
			Quiz quiz = new Quiz();
			quiz.setIndex(++sectionIndex);
			quiz.setSubtitle(subtitleAndContent[0]);
			String[] contentAndAnswer = subtitleAndContent[1]
				.split(Const.ANSWER_START_TAG);
			switch (contentAndAnswer.length) {
			case 1:
			    quiz.setContent(contentAndAnswer[0]);
			    break;

			case 2:
			    quiz.setContent(contentAndAnswer[0]);
			    quiz.setAnswer(contentAndAnswer[1]
				    .split(Const.ANSWER_END_TAG)[0]);
			    break;

			default:
			    break;
			}
			setQuiz(quiz);
		    } else {
			// SECTION (not QUIZ)
			Section section = new Section();
			section.setIndex(++sectionIndex);
			section.setSubtitle(subtitleAndContent[0]);
			section.setContent(subtitleAndContent[1]);
			add(section);
		    }
		} else {
		    if (StringUtils.endsWith(part, H1END)
			    || StringUtils.endsWith(part, H2END)) {
			// only TITLE
			Section section = new Section();
			section.setIndex(++sectionIndex);
			section.setSubtitle(subtitleAndContent[0]);
			add(section);
		    } else {
			// only CONTENTS
			Section section = new Section();
			section.setIndex(++sectionIndex);
			section.setContent(subtitleAndContent[0]);
			add(section);
		    }
		}
	    }
	}
	add(currentChapter);
    }

    private void add(Chapter chapter) {
	if (chapter != null) {
	    chapter.setId(Const.CHAPTER + chapters.size());
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