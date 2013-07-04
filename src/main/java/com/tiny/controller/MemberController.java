package com.tiny.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tiny.common.util.Constant;
import com.tiny.model.Member;
import com.tiny.service.MemberService;

@Controller
public class MemberController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = Constant.MEMBER, method = RequestMethod.GET)
	public ModelAndView member() {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		Member member = memberService.get();
		memberService.setUsage(member);
		model.addAttribute("member", member);
		model.addAttribute("url", Constant.MEMBER);
		mav.addAllObjects(model);
		mav.setViewName("member");
		return mav;
	}
	
	@RequestMapping(value = Constant.MEMBER, method = RequestMethod.POST)
	public ModelAndView memberUpdate(@ModelAttribute Member member) {
		memberService.updateName(member.getName());
		return new ModelAndView(new RedirectView("/list"));
	}
}