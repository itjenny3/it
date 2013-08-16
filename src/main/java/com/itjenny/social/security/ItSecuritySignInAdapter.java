package com.itjenny.social.security;

import javax.annotation.Resource;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import com.itjenny.domain.user.SocialUser;
import com.itjenny.service.user.SocialUserService;

public class ItSecuritySignInAdapter implements SignInAdapter {
	public final static String SIGN_IN_DETAILS_SESSION_ATTRIBUTE_NAME = "com.itjenny.social.security.signInDetails";

	@Resource(name = "socialUserService")
	private SocialUserService socialUserService;

	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest nativeWebRequest) {
		ConnectionKey connectionKey = connection.getKey();
		SocialUser socialUser = socialUserService.findByUserIdAndConnectionKey(localUserId, connectionKey);
		nativeWebRequest.setAttribute(SIGN_IN_DETAILS_SESSION_ATTRIBUTE_NAME, socialUser,
				RequestAttributes.SCOPE_SESSION);
		return null;
	}
}
