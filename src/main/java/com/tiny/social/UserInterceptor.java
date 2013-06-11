package com.tiny.social;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

	private UsersConnectionRepository connectionRepository;

	@Autowired
	private UserCookieGenerator userCookieGenerator;
	
	@Autowired
	private SecurityContext securityContext;

	@Autowired
	public UserInterceptor(UsersConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		rememberUser(request, response);
		handleSignOut(request, response);
		
		// modified by template
		if (securityContext.userSignedIn() || requestForSignIn(request) || requestForListOne(request)) {
			return true;
		} else {
			return requireSignIn(request, response);
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		securityContext.remove();
	}

	private void rememberUser(HttpServletRequest request, HttpServletResponse response) {
		String userId = userCookieGenerator.readUserId(request);
		if (userId == null) {
			return;
		}
		if (!userNotFound(userId)) {
			userCookieGenerator.removeCookie(response);
			return;
		}
		String providerUserId = userCookieGenerator.readProviderUserId(request);
		securityContext.setCurrentUser(new User(userId, providerUserId));
	}

	private void handleSignOut(HttpServletRequest request, HttpServletResponse response) {
		if (securityContext.userSignedIn() && request.getServletPath().startsWith("/signout")) {
			connectionRepository.createConnectionRepository(securityContext.getCurrentUser().getId())
					.removeConnections("facebook");
			userCookieGenerator.removeCookie(response);
			securityContext.remove();
		}
	}

	// added by template.
	private boolean requestForListOne(HttpServletRequest request) {
		String path = request.getServletPath();
		return path.startsWith("/list/") && (path.length() > "/list/".length()) && Character.isDigit(path.charAt(6));
	}

	private boolean requestForSignIn(HttpServletRequest request) {
		return request.getServletPath().startsWith("/signin");
	}

	private boolean requireSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new RedirectView("/signin", true).render(null, request, response);
		return false;
	}

	private boolean userNotFound(String userId) {
		return connectionRepository.createConnectionRepository(userId).findPrimaryConnection(Facebook.class) != null;
	}
}