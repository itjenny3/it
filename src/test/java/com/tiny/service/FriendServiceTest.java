package com.tiny.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.social.facebook.api.FacebookProfile;

import com.tiny.common.CommonMockTest;
import com.tiny.model.Member;
import com.tiny.repository.MemberRepository;

public class FriendServiceTest extends CommonMockTest {
	@Mock
	private FacebookService mockFacebookService;

	@Mock
	private MemberRepository mockMemberRepository;

	@Mock
	private FacebookProfile mockFacebookProfile;

	private List<FacebookProfile> mockFacebookProfiles;

	private final String providerUserId = "providerUserId";

	@Test
	public void getTemplateFriends() {
		// Given
		mockFacebookProfiles = new ArrayList<FacebookProfile>();
		when(mockFacebookProfile.getId()).thenReturn(providerUserId);
		mockFacebookProfiles.add(mockFacebookProfile);
		when(mockFacebookService.getFriends()).thenReturn(mockFacebookProfiles);
		when(mockMemberRepository.getByProviderUserId(anyString())).thenReturn(createMember());

		// When
		FriendService friendService = new FriendService(mockFacebookService, mockMemberRepository);
		List<Member> friends = friendService.getTemplateFriends();

		// Then
		assertThat(friends.size(), is(1));
	}

	private Member createMember() {
		Member member = new Member();
		member.setProviderUserId(providerUserId);
		member.setRegDate(new Date());
		member.setLastLoginDate(new Date());
		return member;
	}
}
