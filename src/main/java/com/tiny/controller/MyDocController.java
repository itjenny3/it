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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tiny.model.MyDoc;
import com.tiny.service.MyDocService;
import com.tiny.social.SecurityContext;

@Controller
public class MyDocController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyDocController.class);

	@Autowired
	private MyDocService myDocService;

	@Autowired
	private SecurityContext securityContext;

	@RequestMapping(value = { "/myDoc" }, method = RequestMethod.POST)
	public ModelAndView saveMyDoc(@RequestParam Integer myDocId, @RequestParam String title,
			@RequestParam String rawContent) {
		ModelAndView mav = new ModelAndView();
		MyDoc myDoc = new MyDoc();
		ModelMap model = new ModelMap();
		myDoc.setTitle(title);
		myDoc.setRawContent(rawContent);
		myDoc.setTag("");
		myDoc.setIsGoal(false);
		List<MyDoc> myDocs = myDocService.getRecently(myDocId);
		myDocs.add(0, myDocService.saveAndGet(myDoc));
		model.addAttribute("myDocs", myDocs);
		model.addAttribute("providerUserId", securityContext.getProviderUserId());
		model.addAttribute("more", false);
		mav.addAllObjects(model);
		mav.setViewName("myDocs");
		return mav;
	}

	@RequestMapping(value = { "/myDoc" }, method = RequestMethod.PUT)
	public @ResponseBody
	String updateDocument(@RequestParam Integer myDocId, @RequestParam String rawContent) {
		MyDoc myDoc = myDocService.get(myDocId);
		myDoc.setRawContent(rawContent);
		return myDocService.updateAndGet(myDoc).getContent();
	}

	@RequestMapping(value = { "/myDoc" }, method = RequestMethod.DELETE)
	public void delete(@RequestParam Integer myDocId) {
		myDocService.delete(myDocId);
	}
}