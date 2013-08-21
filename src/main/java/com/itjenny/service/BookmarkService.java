package com.itjenny.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.itjenny.domain.Bookmark;
import com.itjenny.domain.user.SocialUser;
import com.itjenny.repository.BookmarkRepository;
import com.itjenny.support.security.SessionService;

@Service
public class BookmarkService {
	private final Logger logger = LoggerFactory.getLogger(BookmarkService.class);

	@Autowired
	private SessionService sessionService;

	@Autowired
	private BookmarkRepository bookmarkRepository;

	public void update(String title, Integer chapterIndex) {
		SocialUser loginUser = sessionService.getLoginUser();
		if (Strings.isNullOrEmpty(loginUser.getProviderUserId())) {
			return;
		}
		Bookmark bookmark = new Bookmark();
		bookmark.setProviderUserId(loginUser.getProviderUserId());
		bookmark.setTitle(title);
		bookmark.setChapterIndex(chapterIndex);
		bookmarkRepository.save(bookmark);
	}
}