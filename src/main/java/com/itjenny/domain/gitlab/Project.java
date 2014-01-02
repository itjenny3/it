package com.itjenny.domain.gitlab;

import lombok.Data;

@Data
public class Project {
    private String id;
    private String name;
    private String description;
    private String default_branch;
    private Owner owner = new Owner();
    private String path;
    private String path_with_namespace;
    private String issues_enabled;
    private String merge_requests_enabled;
    private String wall_enabled;
    private String wiki_enabled;
    private String created_at;
    
    @Data
    class Owner {
        private String id;
        private String username;
        private String email;
        private String name;
        private String blocked;
        private String created_at;
    }
}