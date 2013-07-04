package com.tiny.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tiny.common.util.Constant;
import com.tiny.common.util.XssFilter;
import com.tiny.model.MyDoc;
import com.tiny.service.MemberService;
import com.tiny.service.MyDocService;
import com.tiny.social.SecurityContext;

@Controller
public class MainController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private MemberService memberService;

	@Autowired
	private MyDocService myDocService;

	@Autowired
	private SecurityContext securityContext;

	@Autowired
	private XssFilter xssFilter;

	@RequestMapping(value = Constant.MAIN, method = RequestMethod.GET)
	public ModelAndView main(@RequestParam(defaultValue = "2147483647", required = false) int myDocId,
			@RequestParam(defaultValue = "false", required = false) boolean viewRecently) {
		memberService.updateLastLoginTime();

		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		List<MyDoc> myDocs;
		if (viewRecently) {
			myDocs = myDocService.getRecently(myDocId);
		} else {
			myDocs = myDocService.getList(myDocId);
		}
		for (MyDoc myDoc : myDocs) {
			// undo xssFilter
			if (securityContext.getProviderUserId().equals(myDoc.getProviderUserId())) {
				myDoc.setRawContent(xssFilter.undoFilter(myDoc.getContent()));
			}
		}
		model.addAttribute("newMyDoc", new MyDoc());
		model.addAttribute("myDocs", myDocs);
		model.addAttribute("url", Constant.MAIN);

		// for posting
		model.addAttribute("providerUserId", securityContext.getProviderUserId());

		// more
		if (viewRecently || myDocs.size() != Constant.ONEPAGELIMIT) {
			model.addAttribute("more", false);
		} else {
			model.addAttribute("more", true);
		}

		mav.addAllObjects(model);
		if (myDocId == Integer.MAX_VALUE) {
			mav.setViewName("main");
		} else {
			mav.setViewName("myDocs");
		}
		return mav;
	}
}