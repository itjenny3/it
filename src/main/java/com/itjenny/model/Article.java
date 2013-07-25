package com.itjenny.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

import org.apache.commons.lang.StringUtils;
import org.markdown4j.Markdown4jProcessor;

import com.google.common.base.Joiner;
import com.itjenny.common.util.Const;

@Entity
@Data
public class Article {
	@Id
	private String title;
	@Column(columnDefinition = "TEXT")
	private String content;
	private Integer published;

	@Transient
	private String html;
	@Transient
	private List<Paragraph> paragraphs = new LinkedList<Paragraph>();

	public void makeParagraphs() {
		try {
			String[] parts = new Markdown4jProcessor().process(content).split("<h1>|<h2>");
			int i = 0;
			for (String part : parts) {
				if (!"".equals(part)) {
					Paragraph sentence = new Paragraph();
					sentence.setId("div" + i);
					sentence.setCss(Const.CSS[i % Const.CSS.length]);
					String[] subtitleAndContent = part.split("</h1>|</h2>");
					if (subtitleAndContent.length == 2) {
						sentence.setSubtitle(subtitleAndContent[0]);
						if (sentence.getSubtitle().equalsIgnoreCase(Const.QUIZ)) {
							String[] contentAnswerHint = subtitleAndContent[1].split(Joiner.on("|").join(Const.ANSWER,
									Const.HINT));
							switch (contentAnswerHint.length) {
							case 1:
								sentence.setContent(contentAnswerHint[0]);
								break;
							case 2:
								sentence.setContent(contentAnswerHint[0]);
								sentence.setAnswer(contentAnswerHint[1].replaceAll("<br  />", "").replaceAll("\n", "")
										.trim());
								break;
							case 3:
								sentence.setContent(contentAnswerHint[0]);
								sentence.setAnswer(contentAnswerHint[1]);
								sentence.setAnswer(contentAnswerHint[1].replaceAll("<br  />", "").replaceAll("\n", "")
										.trim());
								String a;
								sentence.setHint(contentAnswerHint[2].replaceAll("</p>", "").replace("\n", "").trim());
								break;
							}
						} else {
							sentence.setContent(subtitleAndContent[1]);
						}
					} else {
						// no title
						sentence.setContent(subtitleAndContent[0]);
					}
					paragraphs.add(sentence);
					i++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}