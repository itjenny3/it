package com.itjenny.web.users;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final Logger logger = LoggerFactory
            .getLogger(UsersController.class);

    @Autowired
    private SocialUserService userService;

    @Autowired
    private AutoLoginAuthenticator autoLoginAuthenticator;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserForm());
        return "users/login";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(UserForm user) {
        SocialUser socialUser = userService.createItUser(user.getUserId(),
                user.getEmail());
        autoLoginAuthenticator.login(socialUser.getEmail(),
                socialUser.getRawPassword());
        return "redirect:/";
    }

    @RequestMapping("/{id}")
    public String profileById(@PathVariable Long id) throws Exception {
        SocialUser socialUser = userService.findById(id);
        return String.format("redirect:/users/%d/%s", id,
                URLEncoder.encode(socialUser.getUserId(), "UTF-8"));
    }

    @RequestMapping("/{id}/{userId}")
    public String profile(@PathVariable Long id, @PathVariable String userId,
            Model model) throws Exception {
        model.addAttribute("socialUser", userService.findById(id));
        return "users/profile";
    }

    @RequestMapping("{id}/form")
    public String updateForm(@LoginUser SocialUser loginUser,
            @PathVariable Long id, Model model) throws Exception {
        SocialUser socialUser = userService.findById(id);
        if (!loginUser.isSameUser(socialUser)) {
            throw new IllegalArgumentException("You can't change another user!");
        }

        model.addAttribute("user", new UserForm(socialUser.getUserId(),
                socialUser.getEmail()));
        model.addAttribute("socialUser", socialUser);
        return "users/form";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String update(@LoginUser SocialUser loginUser,
            @PathVariable Long id, UserForm userForm) throws Exception {
        SocialUser socialUser = userService.findById(id);
        if (!loginUser.isSameUser(socialUser)) {
            throw new IllegalArgumentException("You can't change another user!");
        }

        userService.updateItUser(loginUser, userForm.getEmail(),
                userForm.getUserId());

        return "redirect:/users/logout";
    }

    @RequestMapping("{id}/changepassword")
    public String changePasswordForm(@LoginUser SocialUser loginUser,
            @PathVariable Long id, Model model) throws Exception {
        SocialUser socialUser = userService.findById(id);
        if (!loginUser.isSameUser(socialUser)) {
            throw new IllegalArgumentException(
                    "You cann't change another user!");
        }

        model.addAttribute("socialUser", socialUser);
        model.addAttribute("password", new PasswordDto(id));
        return "users/changepassword";
    }

    @RequestMapping(value = "{id}/changepassword", method = RequestMethod.POST)
    public String changePassword(@LoginUser SocialUser loginUser,
            @PathVariable Long id, PasswordDto password, Model model)
            throws Exception {
        SocialUser socialUser = userService.findById(id);

        if (!loginUser.isSameUser(socialUser)) {
            throw new IllegalArgumentException(
                    "You cann't change another user!");
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
