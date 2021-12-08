package model;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;

import play.cache.AsyncCacheApi;

/**
 * Github APIs Interface containing methods for fetching the data from github api endpoints
 * 
 * @author Mrinal Rai
 * @version 1.0
 * @since 2021-11-20
 *
 */
public interface GithubApi {
	
	
	/**
	 * Processes the data recieved from github repository topic's endpoint 
	 * 
	 * @author Mrinal Rai
	 * @param query Topic selected by the user
	 * @param cache	Async cached being used in the main controller
	 * @return CompletionStage<List<Repository>> containing the 10 latest repositories for the topic
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	CompletionStage<List<Repository>> getRepositoryInfo(String query, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
	/**
	 * Processes the data recieved from github repository endpoint 
	 * 
	 * @author Mrinal Rai
	 * @param query Query searched by the user
	 * @param cache	Async cached being used in the main controller
	 * @return CompletionStage<List<Repository>> containing the 10 latest repositories for the topic
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	CompletionStage<List<Repository>> getRepositories(String query, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
	
	public CompletableFuture<CommitStat> getCommitStatistics(String user,String repository, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
	/**
	 * Gets the http-response from the Github repository topic's end point 
	 * 
	 * @author Mrinal Rai
	 * @param query Topic selected by the user
	 * @param per_page Number of pages
	 * @param page Page number
	 * @param sort Parameter used to sort the response
	 * @param cache Async cached being used in the main controller
	 * @return JsonNode Json response from the endpoint
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	CompletableFuture<Object> getResponse(String query, String per_page, String page, String sort, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
	
	/**
	 * Get the CompletableFuture List of repository issues for provided user and repository
	 * @param user User Name of type String
	 * @param repository Repository Name of type String
	 * @param cache Asnyc cached being used in main controller
	 * @return List of Issues as CompletableFuture Object
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @author Akshay
	 * @since 2021-12-07
	 */
	CompletableFuture<List<Issues>> getIssuesFromResponse(String user, String repository, AsyncCacheApi cache) throws InterruptedException,ExecutionException;
	
	
	CompletableFuture<Object> getRepositoryProfileFromResponse(String username, String repository, AsyncCacheApi cache) throws InterruptedException,ExecutionException;
	CompletableFuture<Object> getRepositoryProfileIssuesFromResponse(String username, String repository, AsyncCacheApi cache) throws InterruptedException,ExecutionException;
	CompletableFuture<Object> getRepositoryProfileCollaborationsFromResponse(String username, String repository, AsyncCacheApi cache) throws InterruptedException,ExecutionException;
	
	/**
	 * Fetch user details 
	 * 
	 * @author Siddhartha Jha
	 * @param username selected by the user from query page
	 * @return UserProfile user details
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	UserProfile getUserProfile(String username) throws InterruptedException, ExecutionException ;
	
	/**
	 * Fetch user repositories details 
	 * 
	 * @author Siddhartha Jha
	 * @param username selected by the user from user profile page
	 * @return List<UserRepository> list of repositories details
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	List<UserRepository> getuser_repository(String username) throws InterruptedException, ExecutionException;
	
}
