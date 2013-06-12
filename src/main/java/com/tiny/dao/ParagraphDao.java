package com.tiny.dao;

import java.util.List;

import com.tiny.model.Paragraph;

public interface ParagraphDao {
	public void save(Paragraph qa);
	public List<Paragraph> getAll();
	public Paragraph get(String pid);
	public void delete(String pid);
}