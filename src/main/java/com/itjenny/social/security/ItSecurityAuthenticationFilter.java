package com.itjenny.social.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.RememberMeServices;

import com.itjenny.domain.user.SocialUser;
import com.itjenny.support.security.SessionService;

public class ItSecurityAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	private static Logger log = LoggerFactory.getLogger(ItSecurityAuthenticationFilter.class);

	public final static String DEFAULT_AUTHENTICATION_URL = "/authenticate";

	protected ItSecurityAuthenticationFilter() {
		super(DEFAULT_AUTHENTICATION_URL);
	}

	@Autowired
	private SessionService sessionService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public void setRememberMeServices(RememberMeServices rememberMeServices) {
		super.setRememberMeServices(rememberMeServices);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		if (sessionService.isAuthenticated()) {
			log.debug("already authentication userId is : {}", sessionService.getAuthentication().getPrincipal());
			return sessionService.getAuthentication();
		} else {
			SocialUser signInDetails = (SocialUser) request.getSession().getAttribute(
					ItSecuritySignInAdapter.SIGN_IN_DETAILS_SESSION_ATTRIBUTE_NAME);

			if (signInDetails == null) {
				log.debug("sns login failed. so login anonymous!");
				return new AnonymousAuthenticationToken("itAnonymousAuthenticationToken", "anonymousUser",
						AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
			}

			log.debug("sns login success. login userId : {}", signInDetails.getUserId());

			ItUser userDetails;
			if (signInDetails.isItUser()) {
				ItUserDetailsService itUserDetailsService = (ItUserDetailsService) userDetailsService;
				userDetails = (ItUser) itUserDetailsService.loadUserByEmail(signInDetails.getUserId());
			} else {
				userDetails = (ItUser) userDetailsService.loadUserByUsername(signInDetails.getUserId());
			}
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
			authenticationToken.setDetails(userDetails.getProviderType());
			return authenticationToken;
		}
	}
}
