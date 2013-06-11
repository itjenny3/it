package com.tiny.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiny.dao.ItemDao;
import com.tiny.model.Item;

@Repository
public class ItemRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemRepository.class);

	@Autowired
	private ItemDao itemDao;

	public void save(Item item) {
		itemDao.save(item);
	}
	
	public List<Item> getAll() {
		return itemDao.getAll();
	}

	public Item get(Integer itemId) {
		return itemDao.get(itemId);
	}
}
