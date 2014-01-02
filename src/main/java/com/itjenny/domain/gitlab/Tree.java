package com.itjenny.domain.gitlab;

import lombok.Data;

@Data
public class Tree {
    private String name;
    private String type;
    private String mode;
    private String id;
}