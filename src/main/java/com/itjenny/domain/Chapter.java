package com.itjenny.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Chapter {
    private String id;
    private List<Section> sections = new ArrayList<Section>();
    private Quiz quiz;

    public void add(Section section) {
	sections.add(section);
    }
}