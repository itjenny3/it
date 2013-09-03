package com.itjenny.social.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;

import com.itjenny.domain.user.ExistedUserException;
import com.itjenny.service.user.SocialUserService;

@Controller
@RequestMapping("/signup")
public class ItSecuritySignUpController {
	private String authenticateUrl = ItSecurityAuthenticationFilter.DEFAULT_AUTHENTICATION_URL;

	@Autowired
	private SocialUserService socialUserService;

	@Autowired
	private ItSecuritySignInAdapter itSecuritySignInAdapter;

	public void setAuthenticateUrl(String authenticateUrl) {
		this.authenticateUrl = authenticateUrl;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String signUpForm(ServletWebRequest request, Model model) {
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		ConnectionData connectionData = connection.createData();
		SignUpForm signUpForm = new SignUpForm(connectionData.getDisplayName());
		model.addAttribute("signUpForm", signUpForm);
		return "users/signUpForm";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String signUpSubmit(ServletWebRequest request, SignUpForm signUpForm, BindingResult result) {
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		try {
			socialUserService.createNewSocialUser(signUpForm.getUserId(), connection);
			itSecuritySignInAdapter.signIn(signUpForm.getUserId(), connection, request);
			return "redirect:" + authenticateUrl;
		} catch (ExistedUserException e) {
			result.addError(new FieldError("signUpForm", "userId", signUpForm.getUserId()
					+ " is already existed."));
			return "users/signUpForm";
		}
	}
}
