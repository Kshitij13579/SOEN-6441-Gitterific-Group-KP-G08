package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.cache.AsyncCacheApi;
import service.RepositorySearchService;

public class GithubApiMock implements GithubApi {
	@Override
	public List<Repository> getRepositoryInfo(String query, boolean isTopic, AsyncCacheApi cache) throws InterruptedException, ExecutionException {
		String testResources = System.getProperty("user.dir") + "/test/resources/play.json";
		java.io.File file = new java.io.File(testResources);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = null;
		try {
			json = mapper.readTree(file);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Repository> repoList = new ArrayList<Repository>();
		RepositorySearchService repoService = new RepositorySearchService();
		repoList = repoService.getRepoList(json);
		return repoList;
	}

	@Override
	public CommitStat getCommitStatistics(String user, String repository, AsyncCacheApi cache)
			throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}
}
