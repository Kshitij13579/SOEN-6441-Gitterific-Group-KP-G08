package model;

import java.util.List;
import java.util.concurrent.ExecutionException;

import play.cache.AsyncCacheApi;

public interface GithubApi {
	List<Repository> getRepositoryInfo(String query, boolean isTopic, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
	
	public CommitStat getCommitStatistics(String user,String repository, AsyncCacheApi cache) throws InterruptedException, ExecutionException;
}
