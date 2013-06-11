package com.tiny.dao;

import java.util.List;

import com.tiny.model.ItemOnMember;

public interface ItemOnMemberDao {
	public void save(ItemOnMember itemOnMember);
	public List<ItemOnMember> get(String providerUserId);
}