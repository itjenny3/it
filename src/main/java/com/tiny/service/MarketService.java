package com.tiny.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiny.model.Item;
import com.tiny.repository.ItemRepository;

@Service
public class MarketService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MarketService.class);

	private ItemRepository itemRepository;
	
	@Autowired
	MarketService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	public List<Item> getAll() {
		return itemRepository.getAll();
	}
}