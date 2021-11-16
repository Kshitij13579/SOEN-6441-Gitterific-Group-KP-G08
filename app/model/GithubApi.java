package model;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;

import play.cache.AsyncCacheApi;

public interface GithubApi {
	List<Repository> getRepositoryInfo(String query, boolean isTopic, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
	
	public CommitStat getCommitStatistics(String user,String repository, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
	JsonNode getResponse(String query, String per_page, String page, String sort, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
}
