package com.tiny.dao;

import java.util.List;

import com.tiny.model.Comment;

public interface CommentDao {
	public void save(Comment comment);
	public List<Comment> get(Integer documentId);
	public Comment getCommentId(Integer commentId);
	public Comment getLast();
	public Integer getLastCommentId();
	public Integer count();
	public Integer countByDocumentId(Integer documentId);
	public Integer countCommentForLast1Hour(String providerUserId);
	public void update(Comment comment);
	public void delete(Integer commentId);
	public void deleteWithDocumentId(Integer documentId);
}