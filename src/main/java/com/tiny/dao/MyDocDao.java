package com.tiny.dao;

import java.util.List;
import java.util.Map;

import com.tiny.model.MyDoc;

public interface MyDocDao {
	public void save(MyDoc myDoc);
	public List<MyDoc> getAll();
	public List<MyDoc> getList(Map<String, Object> map);
	public List<MyDoc> getRecently(Integer from);
	public MyDoc get(Integer myDocId);
	public String getProviderUserId(Integer myDocId);
	public MyDoc getLast();
	public Integer count();
	public void update(MyDoc myDoc);
	public void delete(Integer myDocId);
}