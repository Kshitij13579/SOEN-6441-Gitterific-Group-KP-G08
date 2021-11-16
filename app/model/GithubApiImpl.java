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
}
