package model;

import java.util.List;
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
	 * @return List<Repository> containing the 10 latest repositories for the topic
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	List<Repository> getRepositoryInfo(String query, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
	
	public CommitStat getCommitStatistics(String user,String repository, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
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
	JsonNode getResponse(String query, String per_page, String page, String sort, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
	List<Issues> getIssuesFromResponse(String user, String repository, AsyncCacheApi cache) throws InterruptedException,ExecutionException;
	JsonNode getRepositoryProfileFromResponse(String username, String repository, AsyncCacheApi cache) throws InterruptedException,ExecutionException;
	JsonNode getRepositoryProfileIssuesFromResponse(String username, String repository, AsyncCacheApi cache) throws InterruptedException,ExecutionException;
	JsonNode getRepositoryProfileCollaborationsFromResponse(String username, String repository, AsyncCacheApi cache) throws InterruptedException,ExecutionException;
	
}
