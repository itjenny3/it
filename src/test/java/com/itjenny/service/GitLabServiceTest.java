package com.itjenny.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itjenny.common.CommonTest;

public class GitLabServiceTest extends CommonTest {
    @Autowired
    GitLabService gitLabService;

    @Test
    public void getProjects() {
        gitLabService.getProjects();
    }

    @Test
    public void getCommits() {
        gitLabService.getCommits();
    }

    @Test
    public void getTrees() {
        gitLabService.getTrees();
    }
}