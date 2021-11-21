package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.ConfigFactory;

import play.cache.AsyncCacheApi;
import play.libs.ws.*;
import service.CommitStatService;
import service.RepositorySearchService;

public class GithubApiImpl implements GithubApi, WSBodyReadables  {
	/**
	 * Method described in GithubApi Interface
	 * @author Mrinal Rai
	 * @since 2021-11-20
	 */
	@Override
	public List<Repository> getRepositoryInfo(String query, AsyncCacheApi cache) throws InterruptedException, ExecutionException {
		JsonNode jn = getResponse("topic:" + query, ConfigFactory.load().getString("constants.repo_per_page"), 
				ConfigFactory.load().getString("constants.repo_page"), "updated", cache);
		List<Repository> repoList = new ArrayList<Repository>();
		RepositorySearchService repoService = new RepositorySearchService();
		repoList = repoService.getRepoList(jn);
		return repoList;
	};
	
	/**
	 * Method described in GithubApi Interface
	 * @author Mrinal Rai
	 * @since 2021-11-20
	 */
	@Inject WSClient ws;
	public JsonNode getResponse(String query, String per_page, String page, String sort, AsyncCacheApi cache) throws InterruptedException, ExecutionException {
		WSRequest request = ws.url(ConfigFactory.load().getString("constants.git_search_repo_url"))
				.addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"))
				.addQueryParameter(GIT_PARAM.QUERY.value, query)
				.addQueryParameter(GIT_PARAM.PER_PAGE.value, per_page)
				.addQueryParameter(GIT_PARAM.PAGE.value, page)
				.addQueryParameter(GIT_PARAM.SORT.value, sort);
		CompletionStage<JsonNode> jsonPromise = cache.getOrElseUpdate(
				request.getUrl() + GIT_PARAM.QUERY.value + query + GIT_PARAM.PER_PAGE.value 
				+ per_page + GIT_PARAM.PAGE.value + page + GIT_PARAM.SORT.value + sort, () -> request.get().thenApply(r -> r.getBody(json()))
				, Integer.parseInt(ConfigFactory.load().getString("constants.CACHE_EXPIRY_TIME")));
		return jsonPromise.toCompletableFuture().get();
	}
	
	/**
	 * This method is used to retrieve 100 commits from github API and store the statistics in CommitStat model.
	 * @return CommitStat - returns a CommitStat object with statistical information of commits.
	 */
	@Override
	public CommitStat getCommitStatistics(String user, String repository, AsyncCacheApi cache)
			throws InterruptedException, ExecutionException {
		
		CommitStatService commStatService = new CommitStatService();
    	List<Commit> commitList = new ArrayList<Commit>();
    	
    	WSRequest request = ws.url(ConfigFactory.load().getString("constants.git_repositoryprofile_url")+"/"+user+"/"+repository+"/commits")
	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"))
	              .addQueryParameter(GIT_PARAM.PER_PAGE.value, ConfigFactory.load().getString("constants.commits_per_page"))
	              .addQueryParameter(GIT_PARAM.PAGE.value, ConfigFactory.load().getString("constants.commits_page"))
	              .setAuth(ConfigFactory.load().getString("constants.git_user"),ConfigFactory.load().getString("constants.git_token"));

    	CompletionStage<JsonNode> jsonPromise = cache.getOrElseUpdate(user+"-"+repository+"-list", 
    			new Callable<CompletionStage<JsonNode>>() {
    				public CompletionStage<JsonNode> call() {
    					return request.get().thenApply(r -> r.getBody(json()));
    				};
    	}, 3600);
    	
    	
    	CompletableFuture<List<String>> shaList = jsonPromise.toCompletableFuture().thenApply(r -> commStatService.getShaList(r));
    	
    	shaList.get().forEach(sha -> {
    		WSRequest r = ws.url(ConfigFactory.load().getString("constants.git_repositoryprofile_url")+ "/"+user+"/"+repository+"/commits/"+sha)
  	              .addHeader(GIT_HEADER.CONTENT_TYPE.value, ConfigFactory.load().getString("constants.git_header.Content-Type"))
    		      .setAuth(ConfigFactory.load().getString("constants.git_user"),ConfigFactory.load().getString("constants.git_token"));

    		CompletionStage<JsonNode> jsonCommit = cache.getOrElseUpdate(sha, 
        			new Callable<CompletionStage<JsonNode>>() {
        				public CompletionStage<JsonNode> call() {
        					return r.get().thenApply(r -> r.getBody(json()));
        				};
        	}, 3600);
    		
    	    try {
    	    	
				JsonNode temp = jsonCommit.toCompletableFuture().get();
				commitList.add(new Commit(
						       new Author(temp.get("commit").get("author").get("name").asText(), 
						                  (temp.get("author").has("login")) ? temp.get("author").get("login").asText() : "null",
						                  0
						       ),
						       sha,
						       (temp.has("stats")) ? temp.get("stats").get("additions").asInt() : 0 , 
						       (temp.has("stats")) ? temp.get("stats").get("deletions").asInt() : 0 ));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
	public List<Issues> getIssuesFromResponse(String user, String repository, AsyncCacheApi cache) throws InterruptedException,ExecutionException {
	 WSRequest request =
	  ws.url("https://api.github.com/repos/"+user+"/"+repository+"/issues")
	  .addHeader(GIT_HEADER.CONTENT_TYPE.value,
	  ConfigFactory.load().getString("constants.git_header.Content-Type"))
	  .addQueryParameter(GIT_PARAM.PER_PAGE.value,
	  ConfigFactory.load().getString("constants.issues_per_page"))
	  .addQueryParameter(GIT_PARAM.PAGE.value,
	  ConfigFactory.load().getString("constants.issues_page") );  
	  
	  CompletionStage<JsonNode> jsonPromise = cache.getOrElseUpdate(request.getUrl()+ GIT_PARAM.PER_PAGE.value, 
 			new Callable<CompletionStage<JsonNode>>() {
 				public CompletionStage<JsonNode> call() {
 					return request.get().thenApply(r -> r.getBody(json()));
 				};
 	},Integer.parseInt(ConfigFactory.load().getString("constants.CACHE_EXPIRY_TIME")) );
	  
	  JsonNode repoIssues=jsonPromise.toCompletableFuture().get();
	  
	  List<Issues> titleList=new ArrayList<Issues>();
	  
		repoIssues.forEach(t->{ 	
			
			 String title=t.get("title").asText(); 
			 titleList.add(new Issues(title));
			 
			 });
		 
		 return titleList;
	   
	}
	
}
