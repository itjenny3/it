package com.tiny.dao;

import java.util.List;
import java.util.Map;

import com.tiny.model.Document;

public interface DocumentDao {
	public void save(Document document);
	public List<Document> getAll();
	public List<Document> getList(Map<String, Object> map);
	public List<Document> getRecently(Integer from);
	public Document get(Integer documentId);
	public String getProviderUserId(Integer documentId);
	public Document getLast();
	public Integer count();
	public Integer countDocForLast1Hour(String providerUserId);
	public void update(Document document);
	public void increaseComment(Integer documentId);
	public void decreaseComment(Integer documentId);
	public void increaseLike(Integer documentId);
	public void decreaseLike(Integer documentId);
	public void increaseDislike(Integer documentId);
	public void decreaseDislike(Integer documentId);
	public void increaseSharing(Integer documentId);
	public void decreaseSharing(Integer documentId);
	public void delete(Integer documentId);
}