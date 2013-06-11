package com.tiny.dao;

import java.util.List;

import com.tiny.model.MissionOnMember;

public interface MissionOnMemberDao {
	public void save(MissionOnMember memberOnMember);
	public List<MissionOnMember> get(String providerUserId);
}