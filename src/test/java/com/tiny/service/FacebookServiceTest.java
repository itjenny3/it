package com.tiny.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.FriendOperations;
import org.springframework.social.facebook.api.UserOperations;

import com.tiny.common.CommonMockTest;

public class FacebookServiceTest extends CommonMockTest {
	@Mock
	private UserOperations mockUserOperations;

	@Mock
	private FriendOperations mockFriendOperations;

	@Mock
	private FacebookProfile mockFacebookProfile;

	private List<FacebookProfile> mockFacebookProfiles;

	@Mock
	private Facebook mockFacebook;

	@Test
	public void getProfile() {
		// Given
		when(mockFacebookProfile.getEmail()).thenReturn("template@template.com");
		when(mockFacebook.userOperations()).thenReturn(mockUserOperations);
		when(mockFacebook.userOperations().getUserProfile()).thenReturn(mockFacebookProfile);

		// When
		FacebookService facebookService = new FacebookService(mockFacebook);
		FacebookProfile profile = facebookService.getProfile();

		// Then
		assertThat(profile.getEmail(), is("template@template.com"));
	}

	@Test
	public void getFriends() {
		// Given
		mockFacebookProfiles = new ArrayList<FacebookProfile>();
		mockFacebookProfiles.add(mockFacebookProfile);
		when(mockFacebook.friendOperations()).thenReturn(mockFriendOperations);
		when(mockFacebook.friendOperations().getFriendProfiles()).thenReturn(mockFacebookProfiles);

		// When
		FacebookService facebookService = new FacebookService(mockFacebook);
		List<FacebookProfile> friends = facebookService.getFriends();

		// Then
		assertThat(friends.size(), is(1));
	}
}
