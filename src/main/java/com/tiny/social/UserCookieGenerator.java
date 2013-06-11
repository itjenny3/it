package com.tiny.social;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.util.CookieGenerator;

@Component
final class UserCookieGenerator {

	private final CookieGenerator userCookieGenerator = new CookieGenerator();
	private final CookieGenerator userCookieGeneratorForProviderUserId = new CookieGenerator();

	public UserCookieGenerator() {
		userCookieGenerator.setCookieName("userId");
		userCookieGeneratorForProviderUserId.setCookieName("providerUserId");
	}

	public void addCookie(String userId, String providerUserId, HttpServletResponse response) {
		userCookieGenerator.addCookie(response, userId);
		userCookieGeneratorForProviderUserId.addCookie(response, providerUserId);
	}
	
	public void removeCookie(HttpServletResponse response) {
		userCookieGenerator.addCookie(response, "");
		userCookieGeneratorForProviderUserId.addCookie(response, "");
	}

	public String readUserId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(userCookieGenerator.getCookieName())) {
				return cookie.getValue();
			}
		}
		return null;
	}
	
	public String readProviderUserId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(userCookieGeneratorForProviderUserId.getCookieName())) {
				return cookie.getValue();
			}
		}
		return null;
	}
}