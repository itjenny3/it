package com.itjenny.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.itjenny.domain.gitlab.Commit;
import com.itjenny.domain.gitlab.Project;
import com.itjenny.domain.gitlab.Tree;
import com.itjenny.support.utils.ServerProperties;

@Repository
public class GitLabRepository {
    private final Logger logger = LoggerFactory
            .getLogger(GitLabRepository.class);

    @Autowired
    ServerProperties serverProperties;

    private String url = "https://gitlab.com/api/v3/";
    private String privateToken = "gitlab.privateToken";

    private Gson gson = new GsonBuilder().create();
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private HttpGet httpGet = new HttpGet();

    public List<Project> getProjects() {
        List<Project> projects = Collections.emptyList();
        httpGet.setURI(URI.create(url + "projects?private_token="
                + serverProperties.getProperty(privateToken)));
        CloseableHttpResponse response = null;

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            response = httpclient.execute(httpGet);
            inputStream = response.getEntity().getContent();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String temp;
            StringBuilder line = new StringBuilder();
            while ((temp = bufferedReader.readLine()) != null) {
                line.append(temp);
            }
            TypeToken<List<Project>> token = new TypeToken<List<Project>>() {
            };
            projects = gson.fromJson(line.toString(), token.getType());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bufferedReader);
            IOUtils.closeQuietly(inputStreamReader);
            IOUtils.closeQuietly(inputStream);
        }
        return projects;
    }

    public List<Commit> getCommits() {
        List<Commit> commits = Collections.emptyList();
        httpGet.setURI(URI.create(url
                + "/projects/tiny657%2fshow/repository/commits?private_token="
                + serverProperties.getProperty(privateToken)));
        CloseableHttpResponse response = null;

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            response = httpclient.execute(httpGet);
            inputStream = response.getEntity().getContent();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String temp;
            StringBuilder line = new StringBuilder();
            while ((temp = bufferedReader.readLine()) != null) {
                line.append(temp);
            }
            TypeToken<List<Commit>> token = new TypeToken<List<Commit>>() {
            };
            commits = gson.fromJson(line.toString(), token.getType());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bufferedReader);
            IOUtils.closeQuietly(inputStreamReader);
            IOUtils.closeQuietly(inputStream);
        }
        return commits;
    }

    public List<Tree> getTrees() {
        List<Tree> trees = Collections.emptyList();
        httpGet.setURI(URI.create(url
                + "/projects/tiny657%2fshow/repository/tree?private_token="
                + serverProperties.getProperty(privateToken)));
        CloseableHttpResponse response = null;

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            response = httpclient.execute(httpGet);
            inputStream = response.getEntity().getContent();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String temp;
            StringBuilder line = new StringBuilder();
            while ((temp = bufferedReader.readLine()) != null) {
                line.append(temp);
            }
            TypeToken<List<Tree>> token = new TypeToken<List<Tree>>() {
            };
            trees = gson.fromJson(line.toString(), token.getType());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bufferedReader);
            IOUtils.closeQuietly(inputStreamReader);
            IOUtils.closeQuietly(inputStream);
        }
        return trees;
    }

    // not working
    public void getFile() {
        httpGet.setURI(URI.create(url
                + "/projects/tiny657%2fshow/repository/blob?private_token="
                + serverProperties.getProperty(privateToken)));
        CloseableHttpResponse response = null;

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            response = httpclient.execute(httpGet);
            inputStream = response.getEntity().getContent();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String temp;
            StringBuilder line = new StringBuilder();
            while ((temp = bufferedReader.readLine()) != null) {
                line.append(temp);
            }
            TypeToken<List<Commit>> token = new TypeToken<List<Commit>>() {
            };
            List<Commit> commits = gson.fromJson(line.toString(),
                    token.getType());
            System.out.println(commits.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(bufferedReader);
            IOUtils.closeQuietly(inputStreamReader);
            IOUtils.closeQuietly(inputStream);
        }
    }
}