package com.itjenny.repository.user;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.itjenny.domain.user.SocialUser;

public class SocialUserRepositoryImpl implements SocialUserRepositoryCustom {
    @Override
    public List<SocialUser> findsByUserIdAndProviderUserIds(String userId,
            MultiValueMap<String, String> providerUserIds) {
        throw new UnsupportedOperationException(
                "findsByUserIdAndProviderUserIds method not supported!");
    }
}
