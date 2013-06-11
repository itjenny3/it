package com.tiny.dao;

import java.util.List;

import com.tiny.model.DocOnMember;

public interface DocOnMemberDao {
	public void save(DocOnMember docOnMember);
	public List<DocOnMember> get(String providerUserId);
}