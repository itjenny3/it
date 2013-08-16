package com.itjenny.support.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.itjenny.domain.user.SocialUser;
import com.itjenny.support.security.SessionService;

public class GlobalRequestAttributesInterceptor extends HandlerInterceptorAdapter {
    public static final String DEFAULT_LOGIN_USER_REQUEST_KEY = "loginUser";
    
	@Resource (name = "sessionService")
	private SessionService sessionService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		SocialUser loginUser = sessionService.getLoginUser();
        request.setAttribute(DEFAULT_LOGIN_USER_REQUEST_KEY, loginUser);
		
		if (!loginUser.isGuest()) {
//		    request.setAttribute("countNotifications", notificationService.countByNotifiee(loginUser));
		}
		
		return super.preHandle(request, response, handler);
	}
}
