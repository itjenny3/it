package com.tiny.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiny.dao.ItemDao;
import com.tiny.dao.ItemOnMemberDao;
import com.tiny.model.Item;
import com.tiny.model.ItemOnMember;

@Repository
public class ItemOnMemberRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemOnMemberRepository.class);

	@Autowired
	private ItemOnMemberDao itemOnMemberDao;
	
	@Autowired
	private ItemDao itemDao;

	public void save(ItemOnMember itemOnMember) {
		itemOnMemberDao.save(itemOnMember);
	}

	public List<Item> get(String providerUserId) {
		List<Item> items = new ArrayList<Item>();
		List<ItemOnMember> itemsOnMember = itemOnMemberDao.get(providerUserId);
		for (ItemOnMember itemOnMember : itemsOnMember) {
			items.add(itemDao.get(itemOnMember.getItemId()));
		}
		return items;
	}
}
