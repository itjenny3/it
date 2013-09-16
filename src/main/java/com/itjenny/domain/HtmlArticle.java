package com.itjenny.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.markdown4j.Markdown4jProcessor;

import com.itjenny.support.Const;

@Data
public class HtmlArticle {
	private String title;
	private List<Chapter> chapters = new ArrayList<Chapter>();
	private Chapter chapter = null;

	public HtmlArticle(String title, String content) {
		this.title = title;
		try {
			String[] parts = new Markdown4jProcessor().process(content).split("<h1>|<h2>");
			int i = 0;
			for (String part : parts) {
				if (StringUtils.isNotEmpty(part)) {
					String[] subtitleAndContent = part.split("</h1>|</h2>");
					if (subtitleAndContent.length == 2) {
						// TITLE exists.
						if (subtitleAndContent[0].equalsIgnoreCase(Const.QUIZ)) {
							// QUIZ
							Quiz quiz = new Quiz();
							quiz.setCss(Const.CSS[i % Const.CSS.length]);
							quiz.setSubtitle(subtitleAndContent[0]);
							String[] contentAndAnswer = subtitleAndContent[1].split(Const.ANSWER_START_TAG);
							switch (contentAndAnswer.length) {
							case 1:
								quiz.setContent(contentAndAnswer[0]);
								break;

							case 2:
								quiz.setContent(contentAndAnswer[0]);
								quiz.setAnswer(contentAndAnswer[1].split(Const.ANSWER_END_TAG)[0]);
								break;

							default:
								break;
							}
							setQuiz(quiz);
						} else {
							// section (not quiz)
							Section section = new Section();
							section.setCss(Const.CSS[i % Const.CSS.length]);
							section.setSubtitle(subtitleAndContent[0]);
							section.setContent(subtitleAndContent[1]);
							add(section);
						}
					} else {
						// no title
						Section section = new Section();
						section.setContent(subtitleAndContent[0]);
						add(section);
					}
					i++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(chapter);
	}

	private void add(Chapter chapter) {
		if (chapter != null) {
			chapter.setId(Const.CHAPTER + chapters.size());
			chapters.add(chapter);
		}
	}

	private void add(Section section) {
		chapter = ObjectUtils.defaultIfNull(chapter, new Chapter());
		chapter.add(section);
	}

	private void setQuiz(Quiz quiz) {
		chapter = ObjectUtils.defaultIfNull(chapter, new Chapter());
		chapter.setQuiz(quiz);
		add(chapter);
		chapter = null;
	}
}