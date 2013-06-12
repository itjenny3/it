package com.tiny.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiny.dao.ParagraphDao;
import com.tiny.model.Paragraph;

@Repository
public class ParagraphRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphRepository.class);

	@Autowired
	private ParagraphDao paragraphDao;

	public void save(Paragraph paragraph) {
		paragraphDao.save(paragraph);
	}

	public List<Paragraph> getAll() {
		return paragraphDao.getAll();
	}
	
	public Paragraph get(String pid) {
		return paragraphDao.get(pid);
	}
	
	public void delete(String pid) {
		paragraphDao.delete(pid);
	}
}
