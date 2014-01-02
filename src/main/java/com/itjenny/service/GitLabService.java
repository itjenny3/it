package com.itjenny.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itjenny.domain.gitlab.Commit;
import com.itjenny.domain.gitlab.Project;
import com.itjenny.domain.gitlab.Tree;
import com.itjenny.repository.GitLabRepository;

@Service
public class GitLabService {
    private final Logger logger = LoggerFactory.getLogger(GitLabService.class);
    
    @Autowired
    private GitLabRepository gitLabRepository;

    public void getProjects() {
        List<Project> projects = gitLabRepository.getProjects();
        System.out.println(projects.toString());
    }

    public void getCommits() {
        List<Commit> commits = gitLabRepository.getCommits();
        System.out.println(commits.toString());
    }

    public void getTrees() {
        List<Tree> trees = gitLabRepository.getTrees();
        System.out.println(trees.toString());
    }

    // not working
    public void getFile() {
        gitLabRepository.getFile();
    }
}