package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.ConfigFactory;

import model.GIT_PARAM;
import model.Repository;

/**
 * This is the Service Class for Repositories which process json repsonse from API
 * @author Kshitij
 * @version 1.0
 *
 */
public class RepositorySearchService {
   
	/**
	 * Repository Object
	 */
	Repository repository;
	/**
	 * ConfigFactory Object
	 */
	ConfigFactory config;
	
	/**
	 * Empty Repository Search Service Constructor
	 */
	public RepositorySearchService() {
		this.repository = new Repository();
	}
	
	/**
	 * Constructor to initialize to repository search service
	 * @param repository repository object
	 */
	public RepositorySearchService(Repository repository) {
		this.repository = repository;
	}
	
	
    /**
     * This method process json and returns list of repository with details
     * 
     * @param json json response from API call
     * @return list of repositories
     * @throws InterruptedException Exception during runtime
	 * @throws ExecutionException Exception thrown when attempting to 
	 * 							  retrieve the result of any task
     */
    public CompletionStage<List<Repository>> getRepoList(CompletableFuture<Object> json) throws InterruptedException, ExecutionException {
		
		List<Repository> repos= new ArrayList<Repository>();
		CompletionStage<List<Repository>> jsonPromise = json.thenApply(response -> {
			((JsonNode) response).get("items").forEach(items ->{
	        	String login = items.get("owner").get("login").asText();
	        	String name = items.get("name").asText();
	        	String issues_url = items.get("issues_url").asText();
	        	String commits_url = items.get("commits_url").asText();
	        	ArrayList<String> topics = StreamSupport.stream(items.get("topics").spliterator(), true)
	                    .map( num -> num.asText())
	                    .collect(Collectors.toCollection(ArrayList::new));
	        	repos.add(new Repository(login,name,issues_url,commits_url, topics));
	        });
			return repos;
		});
		return jsonPromise;
	}
}
