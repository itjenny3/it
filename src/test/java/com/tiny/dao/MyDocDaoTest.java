package com.tiny.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tiny.common.CommonTest;
import com.tiny.model.MyDoc;

public class MyDocDaoTest extends CommonTest {
	@Autowired
	private MyDocDao myDocDao;

	@Test
	public void save() {
		// Given
		Integer count = myDocDao.count();

		// When
		myDocDao.save(createMyDoc());

		// Then
		assertThat(myDocDao.count(), is(count + 1));
	}

	@Test
	public void get() {
		// Given
		myDocDao.save(createMyDoc());
		MyDoc myDoc = myDocDao.getLast();

		// When
		MyDoc myDoc2 = myDocDao.get(myDoc.getMyDocId());

		// Then
		assertThat(myDoc.getContent(), is(myDoc2.getContent()));
	}

	@Test
	public void getLast() {
		// Given
		myDocDao.save(createMyDoc());

		// When
		MyDoc myDoc = myDocDao.getLast();

		// Then
		assertThat(myDoc.getContent(), is(createMyDoc().getContent()));
	}

	@Test
	public void update() {
		// Given
		myDocDao.save(createMyDoc());
		Integer count = myDocDao.count();

		// When
		myDocDao.update(createMyDoc2());
		
		// Then
		assertThat(myDocDao.count(), is(count));
	}

	@Test
	public void delete() {
		// Given
		myDocDao.save(createMyDoc());
		Integer count = myDocDao.count();

		// When
		myDocDao.delete(myDocDao.getLast().getMyDocId());

		// Then
		assertThat(myDocDao.count(), is(count - 1));
	}

	private MyDoc createMyDoc() {
		MyDoc myDoc = new MyDoc();
		myDoc.setTitle("title");
		myDoc.setContent("content");
		myDoc.setProviderUserId("userId");
		myDoc.setTag("tag");
		myDoc.setIsGoal(true);
		myDoc.setRegDate(new Date());
		return myDoc;
	}
	
	private MyDoc createMyDoc2() {
		MyDoc myDoc = new MyDoc();
		myDoc.setTitle("title2");
		myDoc.setContent("content2");
		myDoc.setProviderUserId("userId2");
		myDoc.setTag("tag2");
		myDoc.setIsGoal(true);
		myDoc.setRegDate(new Date());
		return myDoc;
	}
}
