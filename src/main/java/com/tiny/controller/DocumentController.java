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

import com.tiny.common.util.Constants;
import com.tiny.model.Document;
import com.tiny.service.CommentService;
import com.tiny.service.DocumentService;
import com.tiny.service.MemberService;
import com.tiny.service.PointService;
import com.tiny.social.SecurityContext;

@Controller
public class DocumentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

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

	@RequestMapping(value = Constants.DOCUMENT, method = RequestMethod.POST)
	public ModelAndView saveDocument(@RequestParam Integer documentId,
			@RequestParam String rawContent) {
		ModelAndView mav = new ModelAndView();
		Document document = new Document();
		if (memberService.isChanceToDoc()) {
			ModelMap model = new ModelMap();
			document.setRawContent(rawContent);
			List<Document> documents = documentService.getRecently(documentId);
			documents.add(0, documentService.saveAndGet(document));
			model.addAttribute("documents", documents);
			model.addAttribute("providerUserId", securityContext.getProviderUserId());
			model.addAttribute("more", false);
			pointService.calculatePointToSaveDocument();
			mav.addAllObjects(model);
			mav.setViewName("documents");
		} else {
			mav.setViewName("null");
		}
		return mav;
	}

	@RequestMapping(value = Constants.DOCUMENT, method = RequestMethod.PUT)
	public @ResponseBody
	String updateDocument(@RequestParam Integer documentId, @RequestParam String rawContent) {
		Document document = documentService.get(documentId);
		document.setRawContent(rawContent);
		return documentService.updateAndGet(document).getContent();
	}

	@RequestMapping(value = Constants.DOCUMENT, method = RequestMethod.DELETE)
	public void delete(@RequestParam Integer documentId) {
		pointService.calculatePointToDeleteDocument(documentId);
		commentService.deleteWithDocumentId(documentId);
		documentService.delete(documentId);
	}
}