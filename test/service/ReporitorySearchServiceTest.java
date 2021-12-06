package service;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonNode;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Test;

import model.Repository;
import play.libs.Json;


public class ReporitorySearchServiceTest {

	@Test
	public void test() throws InterruptedException, ExecutionException {
		
		// Calculated RepoList from dummy data
		RepositorySearchService rss=new RepositorySearchService();
		CompletableFuture<Object> json = CompletableFuture.supplyAsync(() -> Json.parse("{\"items\": [{\n\"owner\": {\n  \"login\": \"abc\"\n},\n  \"name\": \"def\",\n  \"issues_url\": \"mno\",\n  \"commits_url\": \"pqr\",\n  \"topics\": [\"java\"]\n}]}"));
		List<Repository> repos = rss.getRepoList(json).toCompletableFuture().get();
		
		// Expected RepoList
		ArrayList<String> topics = new ArrayList<String>();
		topics.add("java");
		Repository expectedRepo = new Repository("abc","def","mno","pqr",topics);
		List<Repository> expectedRepos = new ArrayList<Repository>();
		expectedRepos.add(expectedRepo);
		assertTrue(EqualsBuilder.reflectionEquals(expectedRepos.get(0),repos.get(0)));
	}

}