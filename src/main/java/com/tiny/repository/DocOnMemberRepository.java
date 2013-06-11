package com.tiny.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiny.dao.DocOnMemberDao;
import com.tiny.model.DocOnMember;

@Repository
public class DocOnMemberRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(DocOnMemberRepository.class);

	@Autowired
	private DocOnMemberDao docOnMemberDao;

	public void save(DocOnMember docOnMember) {
		docOnMemberDao.save(docOnMember);
	}

	public List<DocOnMember> get(String providerUserId) {
		return docOnMemberDao.get(providerUserId);
	}
}
