package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.ConfigFactory;

import model.GIT_PARAM;
import model.Repository;

public class RepositorySearchService {
   
	Repository repository;
	ConfigFactory config;
	
	public RepositorySearchService() {
		this.repository = new Repository();
	}
	
	public RepositorySearchService(Repository repository) {
		this.repository = repository;
	}
	
	
    public List<Repository> getRepoList(JsonNode json) throws InterruptedException, ExecutionException {
		
		List<Repository> repos= new ArrayList<Repository>();
		
        json.get("items").forEach(items ->{
        	String login = items.get("owner").get("login").asText();
        	String name = items.get("name").asText();
        	String issues_url = items.get("issues_url").asText();
        	String commits_url = items.get("commits_url").asText();
        	ArrayList<String> topics = StreamSupport.stream(items.get("topics").spliterator(), true)
                    .map( num -> num.asText())
                    .collect(Collectors
                    		.toCollection(ArrayList::new));
        	repos.add(new Repository(login,name,issues_url,commits_url, topics));
        });

		return repos;
		
	}
}
