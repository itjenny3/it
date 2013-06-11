package com.tiny.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiny.dao.MyDocDao;
import com.tiny.model.MyDoc;

@Repository
public class MyDocRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyDocRepository.class);

	@Autowired
	private MyDocDao myDocDao;

	public void save(MyDoc myDoc) {
		myDocDao.save(myDoc);
	}

	public List<MyDoc> getAll() {
		return myDocDao.getAll();
	}
	
	public List<MyDoc> getList(Integer from, Integer limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("myDocId", from);
		map.put("limit", limit);
		return myDocDao.getList(map);
	}
	
	public List<MyDoc> getRecently(Integer from) {
		return myDocDao.getRecently(from);
	}
	
	public MyDoc get(Integer myDocId) {
		return myDocDao.get(myDocId);
	}
	
	public String getProviderUesrId(Integer documentId) {
		return myDocDao.getProviderUserId(documentId);
	}
	
	public MyDoc getLast() {
		return myDocDao.getLast();
	}
	
	public void update(MyDoc myDoc) {
		myDocDao.update(myDoc);
	}

	public void delete(Integer myDocId) {
		myDocDao.delete(myDocId);
	}
}
