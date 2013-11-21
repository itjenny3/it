package com.itjenny.support.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.itjenny.domain.ProviderType;
import com.itjenny.domain.user.SocialUser;
import com.itjenny.service.user.SocialUserService;

@Service
public class SessionService {
    @Autowired
    private SocialUserService socialUserService;

    public SocialUser getLoginUser() {
        if (!isAuthenticated()) {
            return SocialUser.GUEST_USER;
        }

        SocialUser socialUser;
        if (isItUser()) {
            socialUser = socialUserService
                    .findByEmail(getAuthenticatedUserName());
        } else {
            socialUser = socialUserService
                    .findByUserId(getAuthenticatedUserName());
        }
        if (socialUser == null) {
            return SocialUser.GUEST_USER;
        }
        return socialUser;
    }

    public boolean isAuthenticated() {
        return getAuthentication() == null ? false : getAuthentication()
                .isAuthenticated();
    }

    public boolean isItUser() {
        Authentication authentication = getAuthentication();
        Object details = authentication.getDetails();
        if (!(details instanceof ProviderType)) {
            return false;
        }

        ProviderType providerType = (ProviderType) details;
        if (providerType == ProviderType.it) {
            return true;
        }

        return false;
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private String getAuthenticatedUserName() {
        return getAuthentication() == null ? null : getAuthentication()
                .getName();
    }
}
