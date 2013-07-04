package com.tiny.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiny.common.util.Constant;
import com.tiny.common.util.XssFilter;
import com.tiny.model.Document;
import com.tiny.repository.DocumentRepository;
import com.tiny.repository.MemberRepository;
import com.tiny.social.SecurityContext;

@Service
public class DocumentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentService.class);

	@Autowired
	private XssFilter xssFilter;

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private SecurityContext securityContext;

	public void save(Document document) {
		document.setProviderUserId(securityContext.getProviderUserId());
		document.setName(memberRepository.getName(securityContext.getProviderUserId()));
		document.setContent(xssFilter.doFilter(document.getRawContent()));
		documentRepository.save(document);
	}

	@Transactional
	public Document saveAndGet(Document document) {
		save(document);
		Document lastDocument = documentRepository.getLast();
		lastDocument.setRawContent(document.getRawContent());
		return lastDocument;
	}

	public Document updateAndGet(Document document) {
		document.setContent(xssFilter.doFilter(document.getRawContent()));
		documentRepository.update(document);
		return document;
	}

	public List<Document> getAll() {
		List<Document> documents = documentRepository.getAll();

		return documents;
	}
	
	public List<Document> getList(Integer from) {
		List<Document> documents = documentRepository.getList(from, Constant.ONEPAGELIMIT);

		return documents;
	}
	
	public List<Document> getRecently(Integer from) {
		List<Document> documents = documentRepository.getRecently(from);

		return documents;
	}
	
	public Document get(Integer documentId) {
		return documentRepository.get(documentId);
	}

	public Document getLast() {
		return documentRepository.getLast();
	}

	public boolean isMyDoc(Integer documentId) {
		return documentRepository.getProviderUesrId(documentId).equals(securityContext.getProviderUserId());
	}

	public void delete(Integer documentId) {
		documentRepository.delete(documentId);
	}
}