package com.tiny.controller;

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

import com.tiny.common.util.Constant;
import com.tiny.model.Comment;
import com.tiny.service.CommentService;
import com.tiny.service.DocumentService;
import com.tiny.service.MemberService;
import com.tiny.service.PointService;
import com.tiny.social.SecurityContext;

@Controller
public class CommentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private MemberService memberService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private PointService pointService;
	
	@Autowired
	private SecurityContext securityContext;

	@RequestMapping(value = Constant.COMMENT, method = RequestMethod.POST)
	public ModelAndView save(@RequestParam Integer documentId, @RequestParam String content) {
		ModelAndView mav = new ModelAndView();

		boolean isMyDoc = documentService.isMyDoc(documentId);
		if (isMyDoc || memberService.isChanceToComment()) {
			ModelMap model = new ModelMap();
			Comment comment = new Comment();
			comment.setDocumentId(documentId);
			comment.setContent(content);
			comment.setIsMyDoc(isMyDoc);
			model.addAttribute("comment", commentService.saveAndGet(comment));
			model.addAttribute("providerUserId", securityContext.getProviderUserId());
			if (!isMyDoc) {
				pointService.calculatePointToSaveComment(documentId);
			}
			mav.addAllObjects(model);
			mav.setViewName("comment");
		} else {
			mav.setViewName("null");
		}
		return mav;
	}

	@RequestMapping(value = Constant.COMMENT, method = RequestMethod.DELETE)
	public @ResponseBody
	boolean delete(@RequestParam Integer documentId, @RequestParam Integer commentId) {
		if (!documentService.isMyDoc(documentId)) {
			pointService.calculatePointToDeleteComment(documentId, commentId);
		}
		commentService.delete(commentId);
		return true;
	}
}