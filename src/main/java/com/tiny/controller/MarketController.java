package com.tiny.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tiny.common.util.Constants;
import com.tiny.model.Checkbox;
import com.tiny.model.Item;
import com.tiny.service.MarketService;

@Controller
public class MarketController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MarketController.class);

	@Autowired
	private MarketService marketService;

	@RequestMapping(value = Constants.MARKET, method = RequestMethod.GET)
	public ModelAndView getFriends() {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		List<Item> items = marketService.getAll();
		Checkbox checkbox = new Checkbox();
		ArrayList<Boolean> checkboxs = new ArrayList<Boolean>();
		for (int i = 0; i < items.size(); i++) {
			checkboxs.add(false);
		}
		checkbox.setCheckboxs(checkboxs);
		model.addAttribute("items", items);
		model.addAttribute("checkbox", checkbox);
		model.addAttribute("url", Constants.MARKET);
		mav.addAllObjects(model);
		mav.setViewName("market");
		return mav;
	}

	@RequestMapping(value = Constants.MARKET + "/" + Constants.BUY, method = RequestMethod.POST)
	public ModelAndView buy(@ModelAttribute Checkbox checkbox) {
		for (Boolean i : checkbox.getCheckboxs()) {
			System.out.println(i);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("market");
		return mav;
	}
}