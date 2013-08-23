package com.itjenny.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;

import com.itjenny.service.article.BookmarkPK;

@Data
@Entity
@IdClass(BookmarkPK.class)
public class Bookmark {
	@Id
	private String providerUserId;
	@Id
	private String title;

	private Integer chapterIndex;
}