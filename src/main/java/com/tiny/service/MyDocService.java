package com.tiny.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiny.common.util.Constants;
import com.tiny.common.util.XssFilter;
import com.tiny.model.MyDoc;
import com.tiny.repository.MyDocRepository;
import com.tiny.social.SecurityContext;

@Service
public class MyDocService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyDocService.class);

	@Autowired
	private XssFilter xssFilter;

	@Autowired
	private MyDocRepository myDocRepository;

	@Autowired
	private SecurityContext securityContext;

	public void save(MyDoc myDoc) {
		myDoc.setProviderUserId(securityContext.getProviderUserId());
		myDoc.setContent(xssFilter.doFilter(myDoc.getRawContent()));
		myDocRepository.save(myDoc);
	}

	@Transactional
	public MyDoc saveAndGet(MyDoc myDoc) {
		save(myDoc);
		MyDoc lastMyDoc = myDocRepository.getLast();
		lastMyDoc.setRawContent(myDoc.getRawContent());
		return lastMyDoc;
	}

	public MyDoc updateAndGet(MyDoc myDoc) {
		myDoc.setContent(xssFilter.doFilter(myDoc.getRawContent()));
		myDocRepository.update(myDoc);
		return myDoc;
	}

	public List<MyDoc> getAll() {
		List<MyDoc> mydocs = myDocRepository.getAll();

		return mydocs;
	}

	public List<MyDoc> getList(Integer from) {
		List<MyDoc> mydocs = myDocRepository.getList(from, Constants.ONEPAGELIMIT);

		return mydocs;
	}

	public List<MyDoc> getRecently(Integer from) {
		List<MyDoc> mydocs = myDocRepository.getRecently(from);

		return mydocs;
	}

	public MyDoc get(Integer myDocId) {
		return myDocRepository.get(myDocId);
	}

	public MyDoc getLast() {
		return myDocRepository.getLast();
	}

	public void delete(Integer myDocId) {
		myDocRepository.delete(myDocId);
	}
}