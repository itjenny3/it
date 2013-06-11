package com.tiny.dao;

import java.util.List;

import com.tiny.model.Item;

public interface ItemDao {
	public void save(Item item);
	public List<Item> getAll();
	public Item get(Integer itemId);
}