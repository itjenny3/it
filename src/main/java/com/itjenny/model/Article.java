package com.itjenny.model;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

import org.markdown4j.Markdown4jProcessor;

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
	private List<Sentence> sentences = new LinkedList<Sentence>();

	public void setSentence() {
		try {
			String[] paragraphs = new Markdown4jProcessor().process(content).split("<h1>|<h2>");
			int i = 0;
			for (String paragraph : paragraphs) {
				if (!"".equals(paragraph)) {
					Sentence sentence = new Sentence();
					sentence.setId("div" + i);
					sentence.setCss(Const.CSS[i % Const.CSS.length]);
					String[] subtitleAndContent = paragraph.split("</h1>|</h2>");
					if (subtitleAndContent.length == 2) {
						sentence.setSubtitle(subtitleAndContent[0]);
						if (sentence.getSubtitle().equalsIgnoreCase(Const.QUIZ)) {
							String[] contentAnswerHint = subtitleAndContent[1].split("Answer>|Hint>");
							switch(contentAnswerHint.length) {
							case 1:
        						sentence.setContent(contentAnswerHint[0]);
        						break;
							case 2:
        						sentence.setContent(contentAnswerHint[0]);
    							sentence.setAnswer(contentAnswerHint[1]);
    							break;
							case 3:
        						sentence.setContent(contentAnswerHint[0]);
    							sentence.setAnswer(contentAnswerHint[1]);
    							sentence.setHint(contentAnswerHint[2]);
    							break;
							}
						} else {
    						sentence.setContent(subtitleAndContent[1]);
						}
					} else {
						// no title
						sentence.setContent(subtitleAndContent[0]);
					}
					sentences.add(sentence);
					i++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}