package com.tiny.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tiny.common.util.Constants;
import com.tiny.common.util.XssFilter;
import com.tiny.model.Comment;
import com.tiny.model.Document;
import com.tiny.model.Like;
import com.tiny.service.CommentService;
import com.tiny.service.DocumentService;
import com.tiny.service.LikeService;
import com.tiny.service.MemberService;
import com.tiny.social.SecurityContext;

@Controller
public class ListController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ListController.class);

	@Autowired
	private MemberService memberService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private LikeService likeService;

	@Autowired
	private SecurityContext securityContext;

	@Autowired
	private XssFilter xssFilter;

	@RequestMapping(value = Constants.LIST, method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "2147483647", required = false) int documentId,
			@RequestParam(defaultValue = "false", required = false) boolean viewRecently) {
		if (!memberService.isExisted()) {
			return new ModelAndView(new RedirectView("/member"));
		}

		memberService.updateLastLoginTime();

		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		List<Document> documents;
		if (viewRecently) {
			documents = documentService.getRecently(documentId);
		} else {
			documents = documentService.getList(documentId);
		}
		Map<String, List<Comment>> comments = new HashMap<String, List<Comment>>();
		for (Document document : documents) {
			model.addAttribute("newComment" + document.getDocumentId(), new Comment());
			comments.put(Integer.toString(document.getDocumentId()), commentService.get(document.getDocumentId()));

			// like, dislike 클릭 여부
			Like like = likeService.getByProviderUserId(document.getDocumentId());
			if (like != null) {
				if (like.getIsLike()) {
					document.setHasMyLike(true);
				} else {
					document.setHasMyDislike(true);
				}
			}

			// undo xssFilter
			if (securityContext.getProviderUserId().equals(document.getProviderUserId())) {
				document.setRawContent(xssFilter.undoFilter(document.getContent()));
			}
		}
		model.addAttribute("newDocument", new Document());
		model.addAttribute("documents", documents);
		model.addAttribute("comments", comments);
		model.addAttribute("url", Constants.LIST);

		// for posting
		model.addAttribute("providerUserId", securityContext.getProviderUserId());

		// to check chance of doc, comment, like, dislike.
		model.addAttribute("member", memberService.getByProviderUserId());

		// more
		if (viewRecently || documents.size() != Constants.ONEPAGELIMIT) {
			model.addAttribute("more", false);
		} else {
			model.addAttribute("more", true);
		}

		mav.addAllObjects(model);
		if (documentId == Integer.MAX_VALUE) {
			mav.setViewName("list");
		} else {
			mav.setViewName("documents");
		}
		return mav;
	}

	@RequestMapping(value = Constants.LIST + "/{documentId}", method = RequestMethod.GET)
	public ModelAndView listOne(@PathVariable Integer documentId) {
		ModelAndView mav = new ModelAndView();
		ModelMap model = new ModelMap();
		Document document = documentService.get(documentId);
		if (document == null) {
			return new ModelAndView(new RedirectView("/list"));
		}
		Map<String, List<Comment>> comments = new HashMap<String, List<Comment>>();
		model.addAttribute("newComment" + document.getDocumentId(), new Comment());
		comments.put(Integer.toString(document.getDocumentId()), commentService.get(document.getDocumentId()));
		// like, dislike 클릭 여부
		Like like = likeService.getByProviderUserId(documentId);
		if (like != null) {
			if (like.getIsLike()) {
				document.setHasMyLike(true);
			} else {
				document.setHasMyDislike(true);
			}
		}
		model.addAttribute("document", document);
		model.addAttribute("comments", comments);
		model.addAttribute("url", Constants.LIST);

		try {
			// for posting
			model.addAttribute("providerUserId", securityContext.getProviderUserId());
			mav.setViewName("listOne");
		} catch (IllegalStateException e) {
			LOGGER.info("No user logined in.");
			mav.setViewName("listOne");
		}

		mav.addAllObjects(model);
		return mav;
	}
}