package com.itjenny.domain.gitlab;

import lombok.Data;

@Data
public class Commit {
    private String id;
    private String short_id;
    private String title;
    private String author_name;
    private String author_email;
    private String created_at;
}