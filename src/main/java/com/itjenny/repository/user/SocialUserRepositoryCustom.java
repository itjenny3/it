package com.itjenny.repository.user;

import java.util.List;

import com.itjenny.domain.user.SocialUser;

import org.springframework.util.MultiValueMap;

public interface SocialUserRepositoryCustom {
	List<SocialUser> findsByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserIds);
}
