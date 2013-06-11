package com.tiny.model;

import lombok.Data;

@Data
public class Item {
	private Integer itemId;
	private String name;
	private Integer condition;
	private Integer price;
	private String desc;
}