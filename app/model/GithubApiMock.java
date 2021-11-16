package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.cache.AsyncCacheApi;
import service.CommitStatService;
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
		
		CommitStatService commStatService = new CommitStatService();
		List<Commit> commitList = new ArrayList<Commit>();
		
		String path = System.getProperty("user.dir") +"/test/resources/"+user+"_"+repository+".json";
		java.io.File file = new java.io.File(path);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = null;
		try {
			json = mapper.readTree(file);
		} 
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> shaList = commStatService.getShaList(json);
		
		shaList.forEach(sha -> {
			String tempPath = System.getProperty("user.dir") +"/test/resources/"+user+"_"+repository+"_"+sha+".json";
			java.io.File tempFile = new java.io.File(tempPath);
			ObjectMapper tempMapper = new ObjectMapper();
			JsonNode temp = null;
			try {
				temp = tempMapper.readTree(tempFile);
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			commitList.add(new Commit(
				       new Author(temp.get("commit").get("author").get("name").asText(), 
				                  (temp.get("author").has("login")) ? temp.get("author").get("login").asText() : "null",
				                  0
				       ),
				       sha,
				       (temp.has("stats")) ? temp.get("stats").get("additions").asInt() : 0 , 
				       (temp.has("stats")) ? temp.get("stats").get("deletions").asInt() : 0 ));
		});
		
		
		CommitStat commitStat = new CommitStat(commStatService.getTopCommitterList(commitList)
                ,commStatService.getAvgAddition(commitList)
                ,commStatService.getAvgDeletion(commitList)
                ,commStatService.getMaxAddition(commitList)
                ,commStatService.getMaxDeletion(commitList)
                ,commStatService.getMinAddition(commitList)
                ,commStatService.getMinDeletion(commitList)
                ,repository
                );
		
		return commitStat;
	}
}
