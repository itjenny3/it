package com.tiny.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.DuplicateStatusException;
import org.springframework.social.RateLimitExceededException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tiny.common.util.Constants;
import com.tiny.model.Document;
import com.tiny.service.DocumentService;
import com.tiny.service.LikeService;
import com.tiny.service.MemberService;
import com.tiny.service.PointService;
import com.tiny.service.PostService;

@Controller
public class LikeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LikeController.class);

	@Autowired
	private MemberService memberService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private LikeService likeService;

	@Autowired
	private PointService pointService;

	@Autowired
	private PostService postService;

	@RequestMapping(value = Constants.POST, method = RequestMethod.POST)
	public ModelAndView post(@RequestParam Integer documentId, @RequestParam String content) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		try {
			postService.post(content);
			pointService.calculatePointToShare(documentId);
			Document document = documentService.get(documentId);
			model.addAttribute("isSuccess", true);
			model.addAttribute("alertMessage", "Posted.");
			model.addAttribute("document", document);
		} catch (DuplicateStatusException e) {
			model.addAttribute("isSuccess", false);
			model.addAttribute("alertMessage", "Already Posted.");
		} catch (UncategorizedApiException e) {
			model.addAttribute("isSuccess", false);
			model.addAttribute("alertMessage", "Content is empty.");
		} catch (RateLimitExceededException e) {
			model.addAttribute("isSuccess", false);
			model.addAttribute("alertMessage", "The rate limit has been exceeded.");
		} catch (Exception e) {
			model.addAttribute("isSuccess", false);
			model.addAttribute("alertMessage", "Error to post.");
		}
		mav.addAllObjects(model);
		mav.setViewName("postAlert");
		return mav;
	}

	@RequestMapping(value = Constants.LIKE, method = RequestMethod.GET)
	public @ResponseBody
	boolean like(@RequestParam Integer documentId) {
		if (documentService.isMyDoc(documentId)) {
			return false;
		}

		if (likeService.getByProviderUserId(documentId) != null) {
			return false;
		}

		if (memberService.isChanceToLike()) {
			pointService.calculatePointToClickLike(documentId);
			likeService.save(documentId, true);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = Constants.CANCELLIKE, method = RequestMethod.GET)
	public @ResponseBody
	boolean cancelLike(@RequestParam Integer documentId) {
		if (documentService.isMyDoc(documentId)) {
			return false;
		}

		pointService.calculatePointToCancelLike(documentId);
		likeService.delete(documentId);
		return true;
	}

	@RequestMapping(value = Constants.DISLIKE, method = RequestMethod.GET)
	public @ResponseBody
	boolean dislike(@RequestParam Integer documentId) {
		if (documentService.isMyDoc(documentId)) {
			return false;
		}

		if (likeService.getByProviderUserId(documentId) != null) {
			return false;
		}

		if (memberService.isChanceToDislike()) {
			pointService.calculatePointToClickDislike(documentId);
			likeService.save(documentId, false);
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = Constants.CANCELDISLIKE, method = RequestMethod.GET)
	public @ResponseBody
	boolean cancelDislike(@RequestParam Integer documentId) {
		if (documentService.isMyDoc(documentId)) {
			return false;
		}

		pointService.calculatePointToCancelDislike(documentId);
		likeService.delete(documentId);
		return true;
	}
}