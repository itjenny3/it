package com.tiny.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiny.common.util.XssFilter;
import com.tiny.model.Comment;
import com.tiny.repository.CommentRepository;
import com.tiny.repository.MemberRepository;
import com.tiny.social.SecurityContext;

@Service
public class CommentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);

	@Autowired
	private XssFilter xssFilter;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private SecurityContext securityContext;

	public void save(Comment comment) {
		comment.setProviderUserId(securityContext.getProviderUserId());
		comment.setName(memberRepository.getName(securityContext.getProviderUserId()));
		comment.setContent(xssFilter.doFilter(comment.getContent()));
		commentRepository.save(comment);
	}

	@Transactional
	public Comment saveAndGet(Comment comment) {
		save(comment);
		return commentRepository.getLast();
	}

	public List<Comment> get(Integer documentId) {
		List<Comment> comments = commentRepository.get(documentId);
		for (Comment comment : comments) {
			comment.setContent(comment.getContent().replace("\r\n", "<br>").replace(" ", "&nbsp"));
		}

		return comments;
	}

	public Comment getCommentId(Integer commentId) {
		return commentRepository.getCommentId(commentId);
	}

	public void deleteWithDocumentId(Integer documentId) {
		commentRepository.deleteWithDocumentId(documentId);
	}

	public void delete(Integer commentId) {
		commentRepository.delete(commentId);
	}
}