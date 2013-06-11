package com.tiny.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.stereotype.Service;

@Service
public class FacebookService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FacebookService.class);

	private Facebook facebook;
	
	@Autowired
	FacebookService(Facebook facebook) {
		this.facebook = facebook;
	}

	public FacebookProfile getProfile() {
		return facebook.userOperations().getUserProfile();
	}
	
	public List<FacebookProfile> getFriends() {
		return facebook.friendOperations().getFriendProfiles();
	}
	
	public void post(String content) {
		facebook.feedOperations().updateStatus(content);
	}
}
