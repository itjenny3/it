package com.itjenny.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

import org.markdown4j.Markdown4jProcessor;

import com.itjenny.common.util.Const;

@Data
public class HtmlArticle {
	String title;
	List<Chapter> chapters = new ArrayList<Chapter>();
	Chapter chapter = null;

	public HtmlArticle(String title, String content) {
		this.title = title;
		try {
			String[] parts = new Markdown4jProcessor().process(content).split("<h1>|<h2>");
			int i = 0;
			for (String part : parts) {
				if (!"".equals(part)) {
					String[] subtitleAndContent = part.split("</h1>|</h2>");
					if (subtitleAndContent.length == 2) {
						if (subtitleAndContent[0].equalsIgnoreCase(Const.QUIZ)) {
							Quiz quiz = new Quiz();
							quiz.setId(Const.SECTION + i);
							quiz.setNextid(Const.SECTION + (i + 1));
							quiz.setCss(Const.CSS[i % Const.CSS.length]);
							quiz.setSubtitle(subtitleAndContent[0]);
							String[] contentAndAnswer = subtitleAndContent[1].split(Const.ANSWER);
							switch (contentAndAnswer.length) {
							case 1:
								quiz.setContent(contentAndAnswer[0]);
								break;
							case 2:
								quiz.setContent(contentAndAnswer[0]);
								quiz.setAnswer(contentAndAnswer[1].split("</p>")[0]);
								break;
							}
							setQuiz(quiz);
						} else {
							Section section = new Section();
							section.setId(Const.SECTION + i);
							section.setNextid(Const.SECTION + (i + 1));
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

	public void add(Chapter chapter) {
		if (chapter != null) {
			chapter.setId(Const.CHAPTER + chapters.size());
			chapters.add(chapter);
		}
	}

	public Chapter get(Integer index) {
		return chapters.get(index);
	}

	private void add(Section section) {
		if (chapter == null) {
			chapter = new Chapter();
		}
		chapter.add(section);
	}

	private void setQuiz(Quiz quiz) {
		if (chapter == null) {
			chapter = new Chapter();
		}
		chapter.setQuiz(quiz);
		add(chapter);
		chapter = null;
	}
}