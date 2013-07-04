package com.tiny.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tiny.common.util.Constant;
import com.tiny.service.FriendService;

@Controller
public class FriendController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FriendController.class);

	@Autowired
	private FriendService friendService;

	@RequestMapping(value = Constant.FRIEND, method = RequestMethod.GET)
	public ModelAndView getFriends() {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		model.addAttribute("friends", friendService.getTemplateFriends());
		model.addAttribute("url", Constant.FRIEND);
		mav.addAllObjects(model);
		mav.setViewName("friend");
		return mav;
	}
}