package com.tiny.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiny.common.CommonTest;
import com.tiny.model.Document;

public class DocumentDaoTest extends CommonTest {
	@Autowired
	private DocumentDao documentDao;

	@Test
	public void save() {
		// Given
		Integer count = documentDao.count();

		// When
		documentDao.save(createDocument());

		// Then
		assertThat(documentDao.count(), is(count + 1));
	}

	@Test
	public void get() {
		// Given
		documentDao.save(createDocument());
		Document document = documentDao.getLast();

		// When
		Document document2 = documentDao.get(document.getDocumentId());

		// Then
		assertThat(document.getContent(), is(document2.getContent()));
	}

	@Test
	public void getLast() {
		// Given
		documentDao.save(createDocument());

		// When
		Document document = documentDao.getLast();

		// Then
		assertThat(document.getContent(), is(createDocument().getContent()));
	}

	@Test
	public void update() {
		// Given
		documentDao.save(createDocument());
		Integer count = documentDao.count();

		// When
		documentDao.update(createDocument2());

		// Then
		assertThat(documentDao.count(), is(count));
	}

	@Test
	public void delete() {
		// Given
		documentDao.save(createDocument());
		Integer count = documentDao.count();

		// When
		documentDao.delete(documentDao.getLast().getDocumentId());

		// Then
		assertThat(documentDao.count(), is(count - 1));
	}

	private Document createDocument() {
		Document document = new Document();
		document.setContent("content");
		document.setProviderUserId("userId");
		document.setRegDate(new Date());
		return document;
	}
	
	private Document createDocument2() {
		Document document = new Document();
		document.setContent("content2");
		document.setProviderUserId("userId");
		document.setRegDate(new Date());
		return document;
	}
}
