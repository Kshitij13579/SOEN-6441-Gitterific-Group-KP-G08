package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
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
	@Override
	public List<Repository> getRepositoryInfo(String query, boolean isTopic, AsyncCacheApi cache) throws InterruptedException, ExecutionException {
		JsonNode jn = getResponse("topic:" + query, ConfigFactory.load().getString("constants.repo_per_page"), 
				ConfigFactory.load().getString("constants.repo_page"), "updated", cache);
		List<Repository> repoList = new ArrayList<Repository>();
		RepositorySearchService repoService = new RepositorySearchService();
		repoList = repoService.getRepoList(jn);
		return repoList;
	};
	
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
				+ per_page + GIT_PARAM.PAGE.value + page + GIT_PARAM.SORT.value + sort,
				new Callable<CompletionStage<JsonNode>>() {
					public CompletionStage<JsonNode> call() {
						return request.get().thenApply(r -> r.getBody(json()));
					};
				}, Integer.parseInt(ConfigFactory.load().getString("constants.CACHE_EXPIRY_TIME")));
		return jsonPromise.toCompletableFuture().get();
	}
	
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
    	
    	JsonNode commits = jsonPromise.toCompletableFuture().get();
    	
    	List<String> shaList = commStatService.getShaList(commits);
    	
    	shaList.forEach(sha -> {
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
}
