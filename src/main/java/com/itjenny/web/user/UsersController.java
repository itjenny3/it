package com.itjenny.web.user;

import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itjenny.domain.user.PasswordDto;
import com.itjenny.domain.user.SocialUser;
import com.itjenny.service.user.SocialUserService;
import com.itjenny.social.security.AutoLoginAuthenticator;
import com.itjenny.support.web.argumentresolver.LoginUser;
import com.itjenny.web.UserForm;

@Controller
@RequestMapping("/users")
public class UsersController {
	private static final int DEFAULT_SUMMARY_PAGE_SIZE = 5;
	private static final int DEFAULT_PAGE_SIZE = 15;

	@Resource(name = "socialUserService")
	private SocialUserService userService;

	@Resource(name = "autoLoginAuthenticator")
	private AutoLoginAuthenticator autoLoginAuthenticator;

	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new UserForm());
		return "users/login";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(UserForm user, HttpServletRequest request, HttpServletResponse response) {
		SocialUser socialUser = userService.createItUser(user.getUserId(), user.getEmail());
		autoLoginAuthenticator.login(socialUser.getEmail(), socialUser.getRawPassword());
		return "redirect:/";
	}

	@RequestMapping("/fblogout")
	public String logout() {
		return "users/fblogout";
	}

	@RequestMapping("/{id}")
	public String profileById(@PathVariable Long id) throws Exception {
		SocialUser socialUser = userService.findById(id);
		return String.format("redirect:/users/%d/%s", id, URLEncoder.encode(socialUser.getUserId(), "UTF-8"));
	}

	// @RequestMapping("/{id}/{userId}")
	// public String profile(@PathVariable Long id, @PathVariable String userId,
	// Model model) throws Exception {
	// model.addAttribute("questions", qnaService.findsQuestionByWriter(id,
	// createPageableByQuestionUpdatedDate(DEFAULT_PAGE_NO,
	// DEFAULT_SUMMARY_PAGE_SIZE)));
	// model.addAttribute("answers", qnaService.findsAnswerByWriter(id,
	// createPageableByAnswerCreatedDate(DEFAULT_PAGE_NO,
	// DEFAULT_SUMMARY_PAGE_SIZE)));
	// model.addAttribute("socialUser", userService.findById(id));
	// return "users/profile";
	// }

	// @RequestMapping("/{id}/{userId}/questions")
	// public String questions(@PathVariable Long id, Integer page, Model model)
	// throws Exception {
	// page = revisedPage(page);
	// model.addAttribute("questions", qnaService.findsQuestionByWriter(id,
	// createPageableByQuestionUpdatedDate(page, DEFAULT_PAGE_SIZE)));
	// model.addAttribute("socialUser", userService.findById(id));
	// return "users/questions";
	// }

	// @RequestMapping("/{id}/{userId}/answers")
	// public String answers(@PathVariable Long id, Integer page, Model model)
	// throws Exception {
	// page = revisedPage(page);
	// model.addAttribute("answers", qnaService.findsAnswerByWriter(id,
	// createPageableByAnswerCreatedDate(page, DEFAULT_PAGE_SIZE)));
	// model.addAttribute("socialUser", userService.findById(id));
	// return "users/answers";
	// }

	@RequestMapping("{id}/form")
	public String updateForm(@LoginUser SocialUser loginUser, @PathVariable Long id, Model model) throws Exception {
		SocialUser socialUser = userService.findById(id);
		if (!loginUser.isSameUser(socialUser)) {
			throw new IllegalArgumentException("You cann't change another user!");
		}

		model.addAttribute("user", new UserForm(socialUser.getUserId(), socialUser.getEmail()));
		model.addAttribute("socialUser", socialUser);
		return "users/form";
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public String update(@LoginUser SocialUser loginUser, @PathVariable Long id, UserForm userForm) throws Exception {
		SocialUser socialUser = userService.findById(id);
		if (!loginUser.isSameUser(socialUser)) {
			throw new IllegalArgumentException("You cann't change another user!");
		}

		userService.updateItUser(loginUser, userForm.getEmail(), userForm.getUserId());

		return "redirect:/users/logout";
	}

	@RequestMapping("{id}/changepassword")
	public String changePasswordForm(@LoginUser SocialUser loginUser, @PathVariable Long id, Model model)
			throws Exception {
		SocialUser socialUser = userService.findById(id);
		if (!loginUser.isSameUser(socialUser)) {
			throw new IllegalArgumentException("You cann't change another user!");
		}

		model.addAttribute("socialUser", socialUser);
		model.addAttribute("password", new PasswordDto(id));
		return "users/changepassword";
	}

	@RequestMapping(value = "{id}/changepassword", method = RequestMethod.POST)
	public String changePassword(@LoginUser SocialUser loginUser, @PathVariable Long id, PasswordDto password,
			Model model) throws Exception {
		SocialUser socialUser = userService.findById(id);

		if (!loginUser.isSameUser(socialUser)) {
			throw new IllegalArgumentException("You cann't change another user!");
		}

		try {
			userService.changePassword(loginUser, password);
			return "redirect:/users/logout";
		} catch (BadCredentialsException e) {
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("socialUser", socialUser);
			model.addAttribute("password", new PasswordDto(id));
			return "users/changepassword";
		}
	}
}
