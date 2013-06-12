package com.tiny.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiny.dao.QADao;
import com.tiny.model.QA;

@Repository
public class QARepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(QARepository.class);

	@Autowired
	private QADao qaDao;

	public void save(QA qa) {
		qaDao.save(qa);
	}

	public List<QA> getAll() {
		return qaDao.getAll();
	}
	
	public QA get(String qid) {
		return qaDao.get(qid);
	}
	
	public void delete(String qid) {
		qaDao.delete(qid);
	}
}
