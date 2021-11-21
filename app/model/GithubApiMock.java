package model;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.ConfigFactory;

import akka.stream.scaladsl.Source;
import akka.util.ByteString;
import play.api.libs.*;
import play.api.libs.json.JsValue;
import play.api.libs.ws.BodyReadable;
import play.api.libs.ws.WSBodyReadables;
import play.cache.AsyncCacheApi;
import service.CommitStatService;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import scala.xml.Elem;
import service.RepositorySearchService;

public class GithubApiMock implements GithubApi {
	
	/**
	 * Method described in GithubApi Interface
	 * @author Mrinal Rai
	 * @since 2021-11-20
	 */
	@Inject AsyncCacheApi cache;
	@Override
	public List<Repository> getRepositoryInfo(String query, AsyncCacheApi cache) throws InterruptedException, ExecutionException {
		JsonNode json = getResponse("", "", "", "", cache);
		List<Repository> repoList = new ArrayList<Repository>();
		RepositorySearchService repoService = new RepositorySearchService();
		repoList = repoService.getRepoList(json);
		return repoList;
	}
	
	/**
	 * Method described in GithubApi Interface
	 * @author Mrinal Rai
	 * @since 2021-11-20
	 */
	@Inject WSClient ws;
	@Override
	public JsonNode getResponse(String query, String per_page, String page, String sort, AsyncCacheApi cache) throws InterruptedException, ExecutionException {
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
		return json;
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

	@Override
	public List<Issues> getIssuesFromResponse(String user, String repository, AsyncCacheApi cache)
			throws InterruptedException, ExecutionException {
		
		String testJson=System.getProperty("user.dir") +"/test/resources/issues.json";
		
		File tempFile=new File(testJson);
		ObjectMapper objectMapper=new ObjectMapper();
		
		JsonNode tempJson=null;
		
		try {
			tempJson=objectMapper.readTree(tempFile);
			
		}catch (Exception e) {
			
			// TODO: handle exception
		}
		
		List<Issues> titleIssues=new ArrayList<Issues>();
		
		tempJson.forEach(t->{
			
			String title=t.get("title").asText();
			titleIssues.add(new Issues(title));
			
		});
		
		return titleIssues;
	}
}
