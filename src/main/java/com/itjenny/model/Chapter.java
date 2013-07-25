package com.itjenny.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Chapter {
	private List<Section> sections = new ArrayList<Section>();
	private Quiz quiz;
	
	public void addSection(Section section) {
		sections.add(section);
	}
}