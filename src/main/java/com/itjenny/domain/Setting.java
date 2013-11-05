package com.itjenny.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.StringUtils;

import lombok.Data;

@Data
@Entity
public class Setting {
    @Id
    private String userId = StringUtils.EMPTY;

    private Boolean pagination = true;
    private Boolean oneline = true;
    private Integer fontsize = 3;
}