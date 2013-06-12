package com.tiny.dao;

import java.util.List;

import com.tiny.model.QA;

public interface QADao {
	public void save(QA qa);
	public List<QA> getAll();
	public QA get(String qid);
	public void delete(String qid);
}