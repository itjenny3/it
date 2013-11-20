package com.itjenny.domain;

import lombok.Data;

import org.apache.commons.lang3.StringUtils;

@Data
public class Section {
    private Integer index;
    private String subtitle = StringUtils.EMPTY;
    private Integer subtitleSize = 1;
    private String content = StringUtils.EMPTY;
}